package dev.jsojka.basic_ecommerce_shop.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

@Component
public class SessionDebugFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            System.out.println("🟡 SESSION ID: " + session.getId());
            System.out.println("🟡 SESSION ATTRIBUTES:");
            Collections.list(session.getAttributeNames()).forEach(name -> {
                Object value = session.getAttribute(name);
                System.out.println("   " + name + " = " + value);
            });
            System.out.println("🟡 SESSION CREATED TIME: " + new Date(session.getCreationTime()));
            System.out.println("🟡 SESSION LAST ACCESSED: " + new Date(session.getLastAccessedTime()));
        } else {
            System.out.println("🔴 No session found for request: " + request.getRequestURI());
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            System.out.println("🟢 Authenticated: " + auth.isAuthenticated());
//            System.out.println("🟢 Principal: " + auth.getPrincipal());
//            System.out.println("🟢 Authorities: " + auth.getAuthorities());
        } else {
            System.out.println("🔴 No authentication found in context.");
        }

        filterChain.doFilter(request, response);
    }
}