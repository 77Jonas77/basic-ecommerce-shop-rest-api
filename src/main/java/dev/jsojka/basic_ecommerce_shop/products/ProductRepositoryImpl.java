package dev.jsojka.basic_ecommerce_shop.products;

import dev.jsojka.basic_ecommerce_shop.users.JpaUserRepository;
import dev.jsojka.basic_ecommerce_shop.users.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductRepositoryImpl implements IProductRepository {

    private final JpaProductRepository jpaProductRepository;
    private final JpaUserRepository jpaUserRepository;
    private final IProductFileSystemRepository productFileSystemRepository;

    public ProductRepositoryImpl(JpaProductRepository jpaProductRepository, JpaUserRepository jpaUserRepository, IProductFileSystemRepository productFileSystemRepository) {
        this.jpaProductRepository = jpaProductRepository;
        this.jpaUserRepository = jpaUserRepository;
        this.productFileSystemRepository = productFileSystemRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        List<ProductEntity> productEntityList = jpaProductRepository.findAll();
        return productEntityList.stream()
                .map(entity -> ProductMapper.toDomain(entity))
                .toList();

    }

    @Override
    public Product save(Product product) {
        UserEntity user = jpaUserRepository.findById(product.getSeller().getId()).orElseThrow();

        ProductEntity productEntity = jpaProductRepository.save(
                ProductMapper.toEntity(product, user)
        );
        return ProductMapper.toDomain(productEntity);
    }

    @Override
    public void deleteById(UUID productId) {
        jpaProductRepository.deleteById(productId);
    }

    @Override
    public Page<Product> findAll(Pageable pagingSort) {
        return jpaProductRepository.findAll(pagingSort)
                .map(entity -> ProductMapper.toDomain(entity));
    }

    @Override
    public Page<Product> findAllByPublishedStatus(Pageable pagingSort, Boolean publishedStatus) {
        return jpaProductRepository.findByPublishedStatus(publishedStatus, pagingSort)
                .map(entity -> ProductMapper.toDomain(entity));
    }

    @Override
    public Optional<Product> findById(UUID productId) {
        return jpaProductRepository.findById(productId)
                .map(productEntity -> ProductMapper.toDomain(productEntity));

    }

    @Override
    public String getFilenameByProductId(UUID productId) {
        return "";
    }

    @Override
    public void saveFilenameByProductId(UUID productId) {

    }
}
