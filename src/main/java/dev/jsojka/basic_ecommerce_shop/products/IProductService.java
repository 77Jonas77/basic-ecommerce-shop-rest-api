package dev.jsojka.basic_ecommerce_shop.products;

import jakarta.validation.Valid;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface IProductService {
    GetAllProductsResponse getAllProducts();

    CreateProductResponse createProduct(CreateProductRequest request);

    void deleteById(UUID productId);

    GetAllProductsCustomedResponse getAllProductsSorted(int page, int size, String sort);

    GetAllProductsCustomedResponse getAllProductsByPublishedStatusAndSorted(int page, int size,
                                                                            Boolean publishedStatus, String sort);

    PatchProductResponse patchProduct(@Valid PatchProductRequest request, UUID productId);

    PatchProductResponse patchProductImageUpload(UUID productId, MultipartFile file);

    FileSystemResource getProductImage(UUID productId);
}
