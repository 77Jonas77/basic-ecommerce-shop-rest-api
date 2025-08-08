package dev.jsojka.basic_ecommerce_shop.users;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@WebMvcTest(controllers = UserController.class)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @MockitoBean
    private UserServiceImpl userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void UserController_RegisterUser_ReturnsRegisterUserResponse(){
        // Arrange | Given

        // Act | When

        // Assert | Then
    }

}