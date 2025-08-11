package dev.jsojka.basic_ecommerce_shop.products;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductServiceImpl productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void ProductController_CreateProduct_ReturnsCreateProductResponse() throws Exception {
        // Given / Arrange
        CreateProductRequest request = new CreateProductRequest(
                UUID.fromString("a31d55a7-7489-4560-9a3f-8477a2eabbce"),
                "Wireless Headphones",
                new BigDecimal("199.99"),
                "High-quality wireless headphones with noise cancellation, long battery life, and comfort.",
                "SoundMax",
                50,
                ProductCategory.ELECTRONICS,
                true,
                120
        );

        CreateProductResponse response = new CreateProductResponse(
                UUID.fromString("fbbd4eae-cd3f-4e1f-8ab1-5f556fbf8bd5"),
                request.name(),
                request.price(),
                request.description(),
                request.brandName(),
                request.availableQuantityLeft(),
                request.productCategory(),
                request.sellerId(),
                LocalDateTime.of(2025, 8, 8, 14, 20),
                null,
                request.publishedStatus(),
                request.soldNumber()
        );

        String requestJson = objectMapper.writeValueAsString(request);

        // When / Act
        when(productService.createProduct(ArgumentMatchers.any())).thenReturn(response);

        // Assert
        mockMvc.perform(post("/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(requestJson)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.productCategory").value("ELECTRONICS"))
                .andReturn();
    }

    @Test
    void ProductController_GetProductImage_ReturnsImage() throws Exception {
        // Arrange | Given
        File tempFile = File.createTempFile("test-image", ".jpg");
        FileSystemResource fileSystemResource = new FileSystemResource(tempFile);

        // Act | When

        when(productService.getProductImage(any())).thenReturn(fileSystemResource);

        // Assert | Then
        mockMvc.perform(get("/v1/products/{productId}/image", ArgumentMatchers.any()))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileSystemResource.getFilename() + "\""))
                .andExpect(content().contentType(MediaType.APPLICATION_OCTET_STREAM));
    }

}