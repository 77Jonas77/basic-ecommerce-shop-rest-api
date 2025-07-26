package dev.jsojka.basic_ecommerce_shop.users;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(
            Set.of(
                    Permission.ADMIN_CREATE,
                    Permission.ADMIN_READ,
                    Permission.ADMIN_UPDATE,
                    Permission.ADMIN_DELETE
            )
    ),
    SELLER(
            Set.of(
                    Permission.SELLER_CREATE,
                    Permission.SELLER_READ,
                    Permission.SELLER_UPDATE,
                    Permission.SELLER_DELETE
            )
    ),
    BUYER(
            Set.of(
                    Permission.BUYER_CREATE,
                    Permission.BUYER_READ,
                    Permission.BUYER_UPDATE,
                    Permission.BUYER_DELETE
            )
    );

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return this.permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
