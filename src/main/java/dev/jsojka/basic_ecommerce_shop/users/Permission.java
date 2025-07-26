package dev.jsojka.basic_ecommerce_shop.users;

public enum Permission {
    ADMIN_CREATE("admin:create" ),
    ADMIN_READ("admin:read" ),
    ADMIN_UPDATE("admin:update" ),
    ADMIN_DELETE("admin:delete" ),

    SELLER_CREATE("seller:create" ),
    SELLER_READ("seller:read" ),
    SELLER_UPDATE("seller:update" ),
    SELLER_DELETE("seller:delete" ),

    BUYER_CREATE("buyer:create" ),
    BUYER_READ("buyer:read" ),
    BUYER_UPDATE("buyer:update" ),
    BUYER_DELETE("buyer:delete" ),
    ;

    Permission(String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {
        return name;
    }
}
