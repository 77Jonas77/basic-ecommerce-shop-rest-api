package dev.jsojka.basic_ecommerce_shop.auth;

import dev.jsojka.basic_ecommerce_shop.users.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthServiceImpl implements IAuthService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(IAuthRepository authRepository, PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void login(UserDetails userDetails, LoginRequestDTO loginRequest, HttpServletRequest request)
            throws InvalidPasswordException {
        if (!passwordEncoder.matches(loginRequest.password(), userDetails.getPassword())) {
            throw new InvalidPasswordException("Provided invalid password.");
        }
        // Create user Authentication
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(userDetails, loginRequest.password(), userDetails.getAuthorities());
        Authentication authentication = authenticationManager.authenticate(token);

        // Provide Authentication to SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Save security context in HTTP session
        request.getSession(true).setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());
    }

}
