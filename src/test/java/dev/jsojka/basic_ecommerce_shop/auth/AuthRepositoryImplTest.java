package dev.jsojka.basic_ecommerce_shop.auth;

import dev.jsojka.basic_ecommerce_shop.testConfig.AbstractIntegrationTest;
import dev.jsojka.basic_ecommerce_shop.testConfig.TestConfig;
import dev.jsojka.basic_ecommerce_shop.users.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

@ActiveProfiles("test")
@DataJpaTest
@Import({UserRepositoryImpl.class, AuthRepositoryImpl.class})
public class AuthRepositoryImplTest extends AbstractIntegrationTest {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private AuthRepositoryImpl authRepository;

    @AfterEach
    void tearDown(){
        // delete all
    }

    @Test
    void AuthRepository_ExistsByEmail_ReturnTrue(){
        // Arrange
        User exampleUser = new User(
                UUID.randomUUID(),
                "Jonasz",
                "Sojka",
                "jonasz@example.com",
                "securePassword123!",
                Role.BUYER
        );
        userRepository.save(exampleUser);

        // Act
        boolean result = authRepository.existsByEmail(exampleUser.getEmail());

        // Assert
        Assertions.assertThat(result).isTrue();
    }
}