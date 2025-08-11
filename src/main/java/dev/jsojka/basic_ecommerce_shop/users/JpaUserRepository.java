package dev.jsojka.basic_ecommerce_shop.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {

    boolean existsByEmail(@NotBlank @NotNull @Email String email);

    @Override
    Optional<UserEntity> findById(UUID uuid);

    @Query("select u from UserEntity u where u.email= :email")
    Optional<UserEntity> findByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("update UserEntity u set u.role = :role where u.id = :userId")
    void updateRoleById(@Param("userId") UUID userId, @Param("role") Role role);
}
