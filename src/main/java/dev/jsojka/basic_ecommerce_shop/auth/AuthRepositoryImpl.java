package dev.jsojka.basic_ecommerce_shop.auth;

import dev.jsojka.basic_ecommerce_shop.users.JpaUserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepositoryImpl implements IAuthRepository {

    private final JpaUserRepository jpaUserRepository;

    public AuthRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }
}
