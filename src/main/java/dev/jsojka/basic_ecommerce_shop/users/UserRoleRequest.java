package dev.jsojka.basic_ecommerce_shop.users;

import jakarta.validation.constraints.NotNull;

public record UserRoleRequest(@NotNull Role role) {
}
