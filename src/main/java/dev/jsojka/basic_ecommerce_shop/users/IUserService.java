package dev.jsojka.basic_ecommerce_shop.users;

import jakarta.validation.Valid;

import java.util.UUID;

public interface IUserService {
    RegisterUserResponse registerUser(RegisterUserRequest request);

    GetUserResponse findUserById(UUID userId);

    UserRoleResponse updateUserRole(@Valid UserRoleRequest request, UUID userId);
}
