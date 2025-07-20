package dev.jsojka.basic_ecommerce_shop.users;

import java.util.UUID;

public record GetUserResponse(String name, String lastName, UUID userId, String email) {
}
