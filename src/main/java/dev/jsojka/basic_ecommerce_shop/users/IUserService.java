package dev.jsojka.basic_ecommerce_shop.users;

import java.util.UUID;

public interface IUserService {
    RegisterUserResponse registerUser(RegisterUserRequest request);

    GetUserResponse findUserById(UUID userId);
}
