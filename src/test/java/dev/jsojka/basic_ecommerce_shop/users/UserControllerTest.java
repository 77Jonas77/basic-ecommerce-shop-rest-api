package dev.jsojka.basic_ecommerce_shop.users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Locale;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = UserController.class)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @MockitoBean
    private UserServiceImpl userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void UserController_RegisterUser_ReturnsRegisterUserResponse() throws Exception {
        // Arrange | Given
        RegisterUserRequest request = new RegisterUserRequest(
                "Jan",
                "Kowalski",
                "jan.kowalski@example.com",
                "bezpieczneHaslo123"
        );
        UUID userId = UUID.randomUUID();
        RegisterUserResponse response = new RegisterUserResponse(userId);

        String requestJson = objectMapper.writeValueAsString(request);

        // Act | When
        when(userService.registerUser(request)).thenReturn(response);

        // Assert | Then
        mockMvc.perform(post("/v1/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.user_id").isNotEmpty())
                .andExpect(jsonPath("$.user_id").value(userId.toString()));
    }

    @Test
    void UserController_UpdateUserRole_ReturnsUserRoleResponse() throws Exception {
        // Arrange | Given
        Role role = Role.SELLER;
        UserRoleRequest request = new UserRoleRequest(role);
        UserRoleResponse response = new UserRoleResponse(role);

        String requestJson = objectMapper.writeValueAsString(request);

        // Act | When
        when(userService.updateUserRole(any(),any())).thenReturn(response);

        // Assert | Then
        mockMvc.perform(patch("/v1/users/{userId}/role", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role").isNotEmpty())
                .andExpect(jsonPath("$.role").value("SELLER"));
    }

}