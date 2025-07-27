package dev.jsojka.basic_ecommerce_shop.users;

import jakarta.validation.constraints.NotNull;

public record UserRolesRequest(@NotNull Role role) {
}
