package dev.jsojka.basic_ecommerce_shop.users;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "User_Controller", description = "User domain operations.")
@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Register a new user",
            description = "Registers a new user with provided name, last name, email and password."
    )
    @ApiResponse(
            responseCode = "201",
            description = "User registered successfully.",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RegisterUserResponse.class),
                    examples = @ExampleObject(
                            name = "SuccessfulRegistration",
                            summary = "Example successful registration response",
                            value = """
                                    {
                                      "name": "Jonas",
                                      "lastName": "Brothers",
                                      "email": "jsbrothers@example.com",
                                      "password": "password123"
                                    }
                                    """
                    )
            )
    )
    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody @Valid RegisterUserRequest request) {
        RegisterUserResponse response = userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GetUserResponse> registerUser(@PathVariable UUID userId) {
        GetUserResponse response = userService.findUserById(userId);
        return ResponseEntity.ok(response);
    }

    // Surely that is not the best impl :)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PatchMapping("/{userId}/role")
    public ResponseEntity<UserRolesResponse> updateUserRole(@RequestBody @Valid UserRolesRequest request, @PathVariable UUID userId) {
        UserRolesResponse response = userService.updateUserRole(request, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
