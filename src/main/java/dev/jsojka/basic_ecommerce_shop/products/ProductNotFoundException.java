package dev.jsojka.basic_ecommerce_shop.products;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
