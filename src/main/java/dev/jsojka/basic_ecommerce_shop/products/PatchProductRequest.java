package dev.jsojka.basic_ecommerce_shop.products;

import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PatchProductRequest(
        @PositiveOrZero BigDecimal price,
        @Size(min = 20) String description,
        @PositiveOrZero Integer availableQuantityLeft,
        LocalDateTime publishedAtDate,
        LocalDateTime withdrewAtDate,
        Boolean publishedStatus,
        @PositiveOrZero Integer soldNumber
) {
}
