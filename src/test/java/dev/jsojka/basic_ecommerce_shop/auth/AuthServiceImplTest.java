package dev.jsojka.basic_ecommerce_shop.auth;

import dev.jsojka.basic_ecommerce_shop.users.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.stream.Collectors;

import static dev.jsojka.basic_ecommerce_shop.users.Role.*;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private UserDetails userDetails;

    @Mock
    private Authentication authentication;

    @Mock
    private AuthRepositoryImpl authRepository;

    private AuthServiceImpl authService;

    @BeforeEach
    void setup() {
        authService = new AuthServiceImpl(authRepository, passwordEncoder, authenticationManager);
    }

    @Test
    void login_ShouldThrowInvalidPasswordException_WhenPasswordIsInvalid() {
        String password = "wrongPassword";
        String encodedPassword = "hashed";

        LoginRequestDTO requestDTO = new LoginRequestDTO("admin@gmail.com", password);
        Mockito.when(userDetails.getPassword()).thenReturn(encodedPassword);
        Mockito.when(passwordEncoder.matches(password, encodedPassword)).thenReturn(false);

        assertThrows(InvalidPasswordException.class, () -> {
            authService.login(userDetails, requestDTO, request);
        });
        Mockito.verifyNoInteractions(authenticationManager);
    }

    @Test
    void login_ShouldPass_WhenPasswordIsValid() {
        String password = "admin123";
        String encodedPassword = "admin123";
        LoginRequestDTO requestDTO = new LoginRequestDTO("admin@gmail.com", password);

        Mockito.when(userDetails.getPassword()).thenReturn(encodedPassword);
        Mockito.when(passwordEncoder.matches(password, encodedPassword)).thenReturn(true);
        Mockito.when(userDetails.getAuthorities()).thenReturn(Collections.emptySet());

        UsernamePasswordAuthenticationToken expectedToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, Collections.emptyList());

        Mockito.when(authenticationManager.authenticate(Mockito.any())).thenReturn(authentication);
        Mockito.when(request.getSession(true)).thenReturn(session);

        authService.login(userDetails, requestDTO, request);

        Authentication contextAuth = SecurityContextHolder.getContext().getAuthentication();
        assertSame(authentication, contextAuth);
        Mockito.verify(session).setAttribute(
                Mockito.eq(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY),
                Mockito.any(SecurityContext.class)
        );
    }
}