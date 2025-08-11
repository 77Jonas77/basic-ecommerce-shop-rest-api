package dev.jsojka.basic_ecommerce_shop;

import dev.jsojka.basic_ecommerce_shop.auth.AuthServiceImpl;
import dev.jsojka.basic_ecommerce_shop.auth.IAuthService;
import dev.jsojka.basic_ecommerce_shop.users.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static dev.jsojka.basic_ecommerce_shop.users.Role.*;

//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BasicEcommerceShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicEcommerceShopApplication.class, args);
    }

    @Bean
    @Profile("!test")
    public CommandLineRunner initDatabase(
            IUserRepository userRepository, PasswordEncoder passwordEncoder
    ) {
        return args -> {
            if (!userRepository.existsByEmail("admin@gmail.com")) {
                User user = new User(UUID.randomUUID(), "admin", "admin", "admin@gmail.com", passwordEncoder.encode("admin123"), ADMIN);
                userRepository.save(user);
            }

            if (!userRepository.existsByEmail("exampleBuyer@gmail.com")) {
                User user = new User(UUID.randomUUID(), "Example", "Buyer", "exampleBuyer@gmail.com", passwordEncoder.encode("buyer123"), BUYER);
                userRepository.save(user);
            }

            if (!userRepository.existsByEmail("exampleSeller@gmail.com")) {
                User user = new User(UUID.randomUUID(), "Example", "Seller", "exampleSeller@gmail.com", passwordEncoder.encode("seller123"), SELLER);
                userRepository.save(user);
            }
        };
    }
}
