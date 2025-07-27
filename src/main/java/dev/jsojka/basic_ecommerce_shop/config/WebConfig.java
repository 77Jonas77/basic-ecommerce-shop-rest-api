package dev.jsojka.basic_ecommerce_shop.config;

import dev.jsojka.basic_ecommerce_shop.products.StringToProductCategoryConverter;
import dev.jsojka.basic_ecommerce_shop.users.StringToRoleEnumConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToRoleEnumConverter());
        registry.addConverter(new StringToProductCategoryConverter());
    }
}
