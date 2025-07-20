package dev.jsojka.basic_ecommerce_shop.users;

import java.util.UUID;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest request);

    GetUserResponse findUserById(UUID userId);
}
