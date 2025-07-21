package dev.jsojka.basic_ecommerce_shop.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final IAuthService authService;
    private final UserDetailsService userDetailsService;

    public AuthController(IAuthService authService, UserDetailsService userDetailsService) {
        this.authService = authService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDTO loginRequest, HttpSession httpSession, HttpServletRequest request) {
        // not sure whether it's a good implementation
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.email());
        try {
            authService.login(userDetails, loginRequest, request);
        } catch (InvalidPasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
        httpSession.setAttribute("user", userDetails.getUsername());
        return ResponseEntity.ok().build();
    }
}
