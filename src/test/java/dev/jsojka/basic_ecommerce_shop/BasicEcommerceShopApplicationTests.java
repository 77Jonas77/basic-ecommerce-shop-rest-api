package dev.jsojka.basic_ecommerce_shop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest
@ActiveProfiles("test")
class BasicEcommerceShopApplicationTests {

	@Test
	void contextLoads() {
	}

}
