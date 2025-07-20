package dev.jsojka.basic_ecommerce_shop.users;

import jakarta.validation.constraints.*;

public record RegisterUserRequest(
        @NotBlank @Size(max = 50) String name,
        @NotBlank @Size(max = 50) String lastName,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 7, max = 100) String password
) {
}

