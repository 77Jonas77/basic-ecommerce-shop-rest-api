package dev.jsojka.basic_ecommerce_shop.users;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public void save(User user) {
        UserEntity userEntity = new UserEntity(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
        jpaUserRepository.save(userEntity);
    }

    @Override
    public Optional<User> findUserById(UUID userId) {
        return jpaUserRepository.findById(userId)
                .map(userEntity -> new User(
                        userEntity.getId(),
                        userEntity.getName(),
                        userEntity.getLastName(),
                        userEntity.getEmail(),
                        userEntity.getRole()
                ));
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email);
    }
}
