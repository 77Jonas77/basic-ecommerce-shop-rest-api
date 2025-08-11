package dev.jsojka.basic_ecommerce_shop.testConfig;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@TestConfiguration
public class TestConfig {

    @Container
    protected final static PostgreSQLContainer<?> postgres;

    static {
        // build
        postgres = new PostgreSQLContainer<>("postgres:17.5")
                .withDatabaseName("testdb")
                .withUsername("user")
                .withPassword("password");
        // start
        postgres.start();
    }

}
