package dev.jsojka.basic_ecommerce_shop.users;


import org.springframework.core.convert.converter.Converter;

public class StringToRoleEnumConverter implements Converter<String, Role> {
    @Override
    public Role convert(String source) {
        return Role.valueOf(source.toUpperCase()); // exception handling by globalExceptionHandler
    }
}
