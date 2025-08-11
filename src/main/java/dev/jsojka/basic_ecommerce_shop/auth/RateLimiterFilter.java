package dev.jsojka.basic_ecommerce_shop.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RateLimiterFilter extends OncePerRequestFilter {

    private final Map<String, RequestInfo> requestCountPerIpAddress = new ConcurrentHashMap<>();
    private static final int USER_MAX_REQUEST_PER_MINUTE = 25;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String ipAddr = request.getRemoteAddr();
        long now = System.currentTimeMillis();

        RequestInfo currInfo = requestCountPerIpAddress.compute(ipAddr, (k, v) -> {
            if (v == null || now - v.startTime > 60_000) {
                return new RequestInfo(now, new AtomicInteger(1));
            } else {
                v.counter.incrementAndGet();
                return v;
            }
        });

        if (currInfo.counter.get() > USER_MAX_REQUEST_PER_MINUTE) {
            response.setStatus(429);
            response.getWriter().write("Too many requests. Please try again later.");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private static class RequestInfo {
        long startTime;
        AtomicInteger counter;

        public RequestInfo(long startTime, AtomicInteger counter) {
            this.startTime = startTime;
            this.counter = counter;
        }
    }
}
