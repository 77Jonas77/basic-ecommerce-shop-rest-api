package dev.jsojka.basic_ecommerce_shop.products;

import dev.jsojka.basic_ecommerce_shop.users.UserEntity;

public class ProductMapper {

    public static Product toDomain(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getDescription(),
                entity.getBrandName(),
                entity.getAvailableQuantityLeft(),
                entity.getProductCategory(),
                entity.getSeller(),
                entity.getPublishedAtDate(),
                entity.getWithdrewAtDate(),
                entity.isPublishedStatus(),
                entity.getSoldNumber()
        );
    }

    public static ProductEntity toEntity(Product product, UserEntity userEntity) {
        return new ProductEntity(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getBrandName(),
                product.getAvailableQuantityLeft(),
                product.getProductCategory(),
                userEntity,
                product.getPublishedAtDate(),
                product.getWithdrewAtDate(),
                product.isPublishedStatus(),
                product.getSoldNumber()
        );
    }
}
