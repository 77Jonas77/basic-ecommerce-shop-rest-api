package dev.jsojka.basic_ecommerce_shop.users;

import jakarta.validation.constraints.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public class User implements UserDetails {

    @NotNull
    private UUID id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 7, max = 100)
    private String password;

    @NotNull
    private Role role;

    public User() {
    }

    public User(UUID id, String name, String lastName, String email, String password, Role role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(UUID id, String name, String lastName, String email, Role role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public User(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.lastName = userEntity.getLastName();
        this.email = userEntity.getEmail();
        this.password = userEntity.getPassword();
        this.role = userEntity.getRole();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
}
