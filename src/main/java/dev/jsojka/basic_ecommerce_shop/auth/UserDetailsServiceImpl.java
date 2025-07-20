package dev.jsojka.basic_ecommerce_shop.auth;

import dev.jsojka.basic_ecommerce_shop.users.IUserRepository;
import dev.jsojka.basic_ecommerce_shop.users.IUserService;
import dev.jsojka.basic_ecommerce_shop.users.User;
import dev.jsojka.basic_ecommerce_shop.users.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    private final IUserService userService;
    private final IUserRepository userRepository;

    public UserDetailsServiceImpl(IUserService userService, IUserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByEmail(username);
        return user.map( userEntity -> new User(userEntity)).orElseThrow(()->new UsernameNotFoundException("Invalid Username"));
    }
}
