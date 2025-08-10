package dev.jsojka.basic_ecommerce_shop.products;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PatchProductResponse(
        UUID id, String name, BigDecimal price, String description, String brandName,
        int availableQuantityLeft,
        ProductCategory productCategory, UUID sellerId, LocalDateTime publishedAtDate,
        LocalDateTime withdrewAtDate, boolean publishedStatus, int soldNumber, String filename
) {
}
