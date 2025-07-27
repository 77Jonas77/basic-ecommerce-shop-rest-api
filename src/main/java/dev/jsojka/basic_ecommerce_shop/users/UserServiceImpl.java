package dev.jsojka.basic_ecommerce_shop.users;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        // check if user with provided email already exists
        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException("User with email: " + request.email() + "already exists!");
        }

        // Create user
        UUID userId = UUID.randomUUID();
        User user = new User(
                userId,
                request.name(),
                request.lastName(),
                request.email(),
                passwordEncoder.encode(request.password()),
                Role.BUYER
        );

        // Save user to db
        userRepository.save(user);

        return new RegisterUserResponse(userId);
    }

    public GetUserResponse findUserById(UUID userId) {
        // Call for user with provided ID
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));

        return new GetUserResponse(user.getName(), user.getLastName(), user.getId(), user.getEmail());
    }

    @Override
    @Transactional
    public UserRoleResponse updateUserRole(UserRoleRequest request, UUID userId) {

        // Call for user
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));

        // Check if already has provided role
        if (user.getRole().equals(request.role())) {
            return new UserRoleResponse(user.getRole());
        }

        // If user was found then update his role
        user.setRole(request.role());
        userRepository.updateRoleById(user.getId(), request.role());

        return new UserRoleResponse(user.getRole());
    }
}
