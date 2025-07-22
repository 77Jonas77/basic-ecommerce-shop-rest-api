package dev.jsojka.basic_ecommerce_shop.auth;

import dev.jsojka.basic_ecommerce_shop.users.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface IAuthService {

//    User getUserById(UUID userId);
//
//    void deleteByUserId(UUID userId);
//
//    User updateUser(UUID userId);

    void login(UserDetails userDetails, @Valid LoginRequestDTO loginRequest, HttpServletRequest request)
            throws InvalidPasswordException;

}
