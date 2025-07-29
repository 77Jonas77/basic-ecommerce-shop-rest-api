package dev.jsojka.basic_ecommerce_shop.products;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }


//    @GetMapping
//    public ResponseEntity<GetAllProductsResponse> getAllProducts() {
//        GetAllProductsResponse response = productService.getAllProducts();
//        return ResponseEntity.ok(response);
//    }

    @GetMapping
    public ResponseEntity<GetAllProductsCustomedResponse> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(required = false) Boolean publishedStatus,
            @RequestParam(defaultValue = "id,desc") String sort
    ) {
        GetAllProductsCustomedResponse response;
        if (publishedStatus != null) {
            response = productService.getAllProductsByPublishedStatusAndSorted(page, size, publishedStatus, sort);
        } else {
            response = productService.getAllProductsSorted(page, size, sort);
        }
        return response == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @PostMapping
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody @Valid CreateProductRequest request) {
        CreateProductResponse response = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID productId) {
        productService.deleteById(productId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ROLE_SELLER')")
    @PatchMapping("/{productId}")
    public ResponseEntity<PatchProductResponse> patchProduct(@RequestBody @Valid PatchProductRequest request, @PathVariable UUID productId){
        PatchProductResponse response = productService.patchProduct(request, productId);
        return ResponseEntity.ok(response);
    }
}
