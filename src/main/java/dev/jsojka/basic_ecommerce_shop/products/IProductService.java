package dev.jsojka.basic_ecommerce_shop.products;

public interface IProductService {
    GetAllProductsResponse getAllProducts();

    CreateProductResponse createProduct(CreateProductRequest request);
}
