package dev.jsojka.basic_ecommerce_shop.products;

import java.util.List;

public interface IProductRepository {
    List<Product> getAllProducts();
}
