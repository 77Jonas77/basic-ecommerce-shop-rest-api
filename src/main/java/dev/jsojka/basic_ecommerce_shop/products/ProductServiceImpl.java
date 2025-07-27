package dev.jsojka.basic_ecommerce_shop.products;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    private final IProductRepository productRepository;

    public ProductServiceImpl(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public GetAllProductsResponse getAllProducts() {
        List<Product> productList = productRepository.getAllProducts();
        return new GetAllProductsResponse(productList);
    }
}
