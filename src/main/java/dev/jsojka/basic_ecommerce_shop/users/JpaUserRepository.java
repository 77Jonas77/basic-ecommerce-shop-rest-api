package dev.jsojka.basic_ecommerce_shop.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {

    boolean existsByEmail(@NotBlank @NotNull @Email String email);

    @Override
    Optional<UserEntity> findById(UUID uuid);
}
