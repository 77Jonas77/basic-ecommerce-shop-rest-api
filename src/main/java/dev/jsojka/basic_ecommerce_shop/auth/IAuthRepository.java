package dev.jsojka.basic_ecommerce_shop.auth;


public interface IAuthRepository {

    boolean existsByEmail(String email);
}
