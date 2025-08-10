package dev.jsojka.basic_ecommerce_shop.users;

import dev.jsojka.basic_ecommerce_shop.testConfig.AbstractIntegrationTest;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryImplTest extends AbstractIntegrationTest {

}