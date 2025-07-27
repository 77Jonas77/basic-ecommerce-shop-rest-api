package dev.jsojka.basic_ecommerce_shop.products;

import dev.jsojka.basic_ecommerce_shop.users.IUserRepository;
import dev.jsojka.basic_ecommerce_shop.users.User;
import dev.jsojka.basic_ecommerce_shop.users.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ProductServiceImpl implements IProductService {
    private final IProductRepository productRepository;
    private final IUserRepository userRepository;

    public ProductServiceImpl(IProductRepository productRepository, IUserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public GetAllProductsResponse getAllProducts() {
        List<Product> productList = productRepository.getAllProducts();
        return new GetAllProductsResponse(productList);
    }

    @Override
    @Transactional
    public CreateProductResponse createProduct(CreateProductRequest request) {

        // Check if Seller (user) exists and use his ID if yes
        User seller = userRepository.findUserById(request.sellerId())
                .orElseThrow(() -> new UserNotFoundException("User with id " + request.sellerId() + " not found"));

        // Init with correct publication date - ofc depends on BL etc.
        LocalDateTime publishedAtDate;
        publishedAtDate = !request.publishedStatus() ? null : LocalDateTime.now();

        // Save new product
        Product product = productRepository.save(new Product(
                UUID.randomUUID(),
                request.name(),
                request.price(),
                request.description(),
                request.brandName(),
                request.availableQuantityLeft(),
                request.productCategory(),
                seller,
                publishedAtDate,
                null,
                request.publishedStatus(),
                0
        ));

        // Definitely need a mapper :)
        return new CreateProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getBrandName(),
                product.getAvailableQuantityLeft(),
                product.getProductCategory(),
                product.getSeller().getId(),
                product.getPublishedAtDate(),
                product.getWithdrewAtDate(),
                product.isPublishedStatus(),
                product.getSoldNumber()
        );
    }

    @Override
    public void deleteById(UUID productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public GetAllProductsCustomedResponse getAllProductsSorted(int page, int size, String sort) {
        // Create Order object to sort products
        Sort.Order order = getOrder(sort);

        // Send query to db providing Pageable object
        Pageable pagingSort = PageRequest.of(page, size, Sort.by(order));
        Page<Product> productsPage = productRepository.findAll(pagingSort);

        // Base on response from db return correct response to controller
        return getGetAllProductsCustomedResponse(productsPage);
    }

    @Override
    public GetAllProductsCustomedResponse getAllProductsByPublishedStatusAndSorted(int page, int size, Boolean publishedStatus, String sort) {
        // Create Order object to sort products
        Sort.Order order = getOrder(sort);

        // Send query to db providing Pageable object
        Pageable pagingSort = PageRequest.of(page, size, Sort.by(order));
        Page<Product> productsPage = productRepository.findAllByPublishedStatus(pagingSort, publishedStatus);

        return getGetAllProductsCustomedResponse(productsPage);
    }

    private GetAllProductsCustomedResponse getGetAllProductsCustomedResponse(Page<Product> productsPage) {
        if (!productsPage.getContent().isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("products", productsPage.getContent());
            response.put("currentPage", productsPage.getNumber());
            response.put("totalItems", productsPage.getTotalElements());
            response.put("totalPages", productsPage.getTotalPages());
            return new GetAllProductsCustomedResponse(response);
        }
        return null;
    }

    private static Sort.Order getOrder(String sort) {
        // sortOrder = "field, direction"
        String[] sortOrder = sort.split(",");
        return new Sort.Order(Sort.Direction.valueOf(sortOrder[1].toUpperCase()), sortOrder[0]);
    }


}
