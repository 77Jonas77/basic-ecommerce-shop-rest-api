package dev.jsojka.basic_ecommerce_shop.auth;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException(String message) {
        super(message);
    }
}
