package dev.jsojka.basic_ecommerce_shop.auth;

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
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

@ActiveProfiles("test")
@Testcontainers
@DataJpaTest
@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({UserRepositoryImpl.class, AuthRepositoryImpl.class})
class AuthRepositoryImplTest {

    @Container
//    @ServiceConnection
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17.5")
            .withDatabaseName("testdb")
            .withUsername("user")
            .withPassword("password");

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private AuthRepositoryImpl authRepository;

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.properties.hibernate.dialect", () -> "org.hibernate.dialect.PostgreSQLDialect");
    }

    @Test
    void connectionEstablished(){
        Assertions.assertThat(postgres.isCreated()).isTrue();
        Assertions.assertThat(postgres.isRunning()).isTrue();
    }

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