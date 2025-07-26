package dev.jsojka.basic_ecommerce_shop.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(@NotBlank @Email String email, @NotBlank String password) {

}
