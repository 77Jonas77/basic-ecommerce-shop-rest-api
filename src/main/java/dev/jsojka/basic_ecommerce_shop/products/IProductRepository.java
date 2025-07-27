package dev.jsojka.basic_ecommerce_shop.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IProductRepository {
    List<Product> getAllProducts();

    Product save(Product product);

    void deleteById(UUID productId);

    Page<Product> findAll(Pageable pagingSort);

    Page<Product> findAllByPublishedStatus(Pageable pagingSort, Boolean publishedStatus);
}
