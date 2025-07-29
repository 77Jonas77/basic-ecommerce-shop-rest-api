package dev.jsojka.basic_ecommerce_shop.products;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateProductRequest(
        @NotNull UUID sellerId,
        @NotBlank String name,
        @NotNull @PositiveOrZero BigDecimal price,
        @NotBlank @Size(min = 20) String description,
        @NotBlank String brandName,
        @PositiveOrZero Integer availableQuantityLeft,
        @NotNull ProductCategory productCategory,
        @NotNull Boolean publishedStatus,
        @PositiveOrZero Integer soldNumber
) {
}
