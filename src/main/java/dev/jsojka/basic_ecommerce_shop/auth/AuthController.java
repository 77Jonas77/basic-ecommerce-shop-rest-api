package dev.jsojka.basic_ecommerce_shop.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Void> login(@RequestBody @Valid LoginRequestDTO loginRequest, HttpSession httpSession, HttpServletRequest request) {
        // not sure whether it's a good implementation, but it works :)
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.email());
        try {
            authService.login(userDetails, loginRequest, request);
        } catch (InvalidPasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        httpSession.setAttribute("user", userDetails.getUsername());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/success-logout-url")
    public ResponseEntity<String> successfulLogout(){
        return ResponseEntity.ok().body("You've been logged out succesfully");
    }
}
