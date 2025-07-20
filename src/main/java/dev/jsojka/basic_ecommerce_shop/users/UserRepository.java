package dev.jsojka.basic_ecommerce_shop.users;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    boolean existsByEmail(String email);

    void save(User user);

    Optional<User> findUserById(UUID userId);
}
