package dev.jsojka.basic_ecommerce_shop.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaProductRepository extends JpaRepository<ProductEntity, UUID> {
    Page<ProductEntity> findByPublishedStatus(boolean publishedStatus, Pageable pageable);
}
