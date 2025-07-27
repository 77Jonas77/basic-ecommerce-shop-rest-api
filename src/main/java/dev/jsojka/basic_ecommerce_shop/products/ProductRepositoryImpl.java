package dev.jsojka.basic_ecommerce_shop.products;

import dev.jsojka.basic_ecommerce_shop.users.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements IProductRepository {
    private final JpaProductRepository jpaProductRepository;

    public ProductRepositoryImpl(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
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
}
