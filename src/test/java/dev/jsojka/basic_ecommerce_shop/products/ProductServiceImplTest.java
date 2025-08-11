package dev.jsojka.basic_ecommerce_shop.products;

import dev.jsojka.basic_ecommerce_shop.users.IUserRepository;
import dev.jsojka.basic_ecommerce_shop.users.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private IProductRepository productRepository;

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IProductFileSystemRepository fileSystemRepository;

    @Test
    public void ProductServiceImpl_CreateProduct_ReturnsCreateProductResponse(){
        // Arrange
        CreateProductRequest productRequest = new CreateProductRequest(
                UUID.randomUUID(),
                "Super Cool Product",
                new BigDecimal("99.99"),
                "This is a very detailed description with at least 20 characters.",
                "BrandX",
                100,
                ProductCategory.ELECTRONICS,
                true,
                10
        );

        User mockedUser = new User();
        mockedUser.setId(productRequest.sellerId());

        Product mockedProduct = new Product(
                UUID.randomUUID(),
                productRequest.name(),
                productRequest.price(),
                productRequest.description(),
                productRequest.brandName(),
                productRequest.availableQuantityLeft(),
                productRequest.productCategory(),
                mockedUser,
                LocalDateTime.now(),
                null,
                productRequest.publishedStatus(),
                productRequest.soldNumber()
        );

        when(userRepository.findUserById(productRequest.sellerId())).thenReturn(Optional.of(mockedUser));
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(mockedProduct);

        // Act
        CreateProductResponse response = productService.createProduct(productRequest);

        // Assert
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.sellerId()).isEqualTo(productRequest.sellerId());
        Assertions.assertThat(response.name()).isEqualTo(productRequest.name());
        Assertions.assertThat(response.price()).isEqualByComparingTo(productRequest.price());
        Assertions.assertThat(response.description()).isEqualTo(productRequest.description());
        Assertions.assertThat(response.brandName()).isEqualTo(productRequest.brandName());
        Assertions.assertThat(response.availableQuantityLeft()).isEqualTo(productRequest.availableQuantityLeft());
        Assertions.assertThat(response.productCategory()).isEqualTo(productRequest.productCategory());
        Assertions.assertThat(response.publishedStatus()).isEqualTo(productRequest.publishedStatus());
        Assertions.assertThat(response.soldNumber()).isEqualTo(mockedProduct.getSoldNumber());
    }
}