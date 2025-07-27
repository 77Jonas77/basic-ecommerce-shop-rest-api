package dev.jsojka.basic_ecommerce_shop.products;

import dev.jsojka.basic_ecommerce_shop.users.User;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateProductRequest(UUID sellerId, String name, BigDecimal price, String description, String brandName,
                                   int availableQuantityLeft, ProductCategory productCategory,
                                   @NotNull boolean publishedStatus, int soldNumber
) {
}
