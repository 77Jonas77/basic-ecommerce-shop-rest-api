package dev.jsojka.basic_ecommerce_shop.auth;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
