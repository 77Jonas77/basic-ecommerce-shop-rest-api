package dev.jsojka.basic_ecommerce_shop.products;

import org.springframework.core.convert.converter.Converter;

public class StringToProductCategoryConverter implements Converter<String,ProductCategory> {
    @Override
    public ProductCategory convert(String source) {
        return ProductCategory.valueOf(source.toUpperCase());
    }
}
