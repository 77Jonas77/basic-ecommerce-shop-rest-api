package dev.jsojka.basic_ecommerce_shop.products;

import dev.jsojka.basic_ecommerce_shop.users.JpaUserRepository;
import dev.jsojka.basic_ecommerce_shop.users.User;
import dev.jsojka.basic_ecommerce_shop.users.UserEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements IProductRepository {
    private final JpaProductRepository jpaProductRepository;
    private final JpaUserRepository jpaUserRepository;

    public ProductRepositoryImpl(JpaProductRepository jpaProductRepository, JpaUserRepository jpaUserRepository) {
        this.jpaProductRepository = jpaProductRepository;
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        List<ProductEntity> productEntityList = jpaProductRepository.findAll();
        return productEntityList.stream()
                .map(p -> new Product(
                                p.getId(),
                                p.getName(),
                                p.getPrice(),
                                p.getDescription(),
                                p.getBrandName(),
                                p.getAvailableQuantityLeft(),
                                p.getProductCategory(),
                                new User(p.getSeller()),
                                p.getPublishedAtDate(),
                                p.getWithdrewAtDate(),
                                p.isPublishedStatus(),
                                p.getSoldNumber()
                        )
                ).toList();
    }

    @Override
    public Product save(Product product) {
        UserEntity user = jpaUserRepository.findById(product.getSeller().getId()).orElseThrow();

        ProductEntity productEntity = jpaProductRepository.save(
                new ProductEntity(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getDescription(),
                        product.getBrandName(),
                        product.getAvailableQuantityLeft(),
                        product.getProductCategory(),
                        user,
                        product.getPublishedAtDate(),
                        product.getSoldNumber(),
                        product.isPublishedStatus()
                )
        );
        // should add mapper or smth
        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice(),
                productEntity.getDescription(),
                productEntity.getBrandName(),
                productEntity.getAvailableQuantityLeft(),
                productEntity.getProductCategory(),
                user,
                productEntity.getPublishedAtDate(),
                productEntity.getWithdrewAtDate(),
                productEntity.isPublishedStatus(),
                productEntity.getSoldNumber()
        );
    }
}
