package dev.jsojka.basic_ecommerce_shop.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jsojka.basic_ecommerce_shop.users.Role;
import dev.jsojka.basic_ecommerce_shop.users.User;
import dev.jsojka.basic_ecommerce_shop.users.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.assertj.core.api.AssertJProxySetup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@DisplayName("AuthController test case")
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthServiceImpl authService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Test
    public void AuthController_Login_ReturnSuccessLogin() throws Exception {
        // Arrange
        UserDetails exampleUser = new User(
                UUID.randomUUID(),
                "Jonasz",
                "Sojka",
                "jonasz@example.com",
                "securePassword123!",
                Role.BUYER
        );

        LoginRequestDTO loginRequestDTO = new LoginRequestDTO(exampleUser.getUsername(), exampleUser.getPassword());

        when(userDetailsService.loadUserByUsername(exampleUser.getUsername())).thenReturn(exampleUser);
        doNothing().when(authService).login(Mockito.any(), Mockito.any(), Mockito.any());

        ResultActions response = mockMvc.perform(post("/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(loginRequestDTO)))
                .andExpect(status().isOk());

        HttpSession session = response.andReturn().getRequest().getSession(false);
        Assertions.assertNotNull(session);
        Assertions.assertEquals(exampleUser.getUsername(), session.getAttribute("user"));
    }
}