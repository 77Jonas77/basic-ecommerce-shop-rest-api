package dev.jsojka.basic_ecommerce_shop.products;

import dev.jsojka.basic_ecommerce_shop.users.IUserRepository;
import dev.jsojka.basic_ecommerce_shop.users.User;
import dev.jsojka.basic_ecommerce_shop.users.UserNotFoundException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;
    private final IUserRepository userRepository;
    private final IProductFileSystemRepository fileSystemRepository;

    public ProductServiceImpl(IProductRepository productRepository, IUserRepository userRepository, IProductFileSystemRepository fileSystemRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.fileSystemRepository = fileSystemRepository;
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

    public PatchProductResponse patchProduct(PatchProductRequest request, UUID productId) {
        // Find existing product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + productId + " not found."));

        // Override/update attributes only if values are not null
        // todo: need better impl (Reflection?/ MapStruct?/ Lombok?
        if (request.price() != null) {
            product.setPrice(request.price());
        }

        if (request.description() != null) {
            product.setDescription(request.description());
        }

        if (request.availableQuantityLeft() != null) {
            product.setAvailableQuantityLeft(request.availableQuantityLeft());
        }

        if (request.publishedAtDate() != null) {
            // might also need validation future or now?
            product.setPublishedAtDate(request.publishedAtDate());
        }

        if (request.withdrewAtDate() != null) {
            // could be for sure in Product class - cause domain logic
            // todo: move Product/ User related validations to Domain classes
            if (request.withdrewAtDate().isBefore(product.getPublishedAtDate())) {
                throw new WithdrewDateInvalidException("WithdrewDate can't be before published date.");
            }
            product.setWithdrewAtDate(request.withdrewAtDate());
            System.out.println(product.getWithdrewAtDate() + " || " + request.withdrewAtDate());
        }

        if (request.publishedStatus() != null) {
            product.setPublishedStatus(request.publishedStatus());
        }

        if (request.soldNumber() != null) {
            product.setSoldNumber(request.soldNumber());
        }

        // Save updated product
        Product savedProduct = productRepository.save(product);

        // Return response
        return new PatchProductResponse(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getPrice(),
                savedProduct.getDescription(),
                savedProduct.getBrandName(),
                savedProduct.getAvailableQuantityLeft(),
                savedProduct.getProductCategory(),
                savedProduct.getSeller().getId(),
                savedProduct.getPublishedAtDate(),
                savedProduct.getWithdrewAtDate(),
                savedProduct.isPublishedStatus(),
                savedProduct.getSoldNumber(),
                savedProduct.getFilename()
        );
    }

    @Override
    @Transactional
    public PatchProductResponse patchProductImageUpload(UUID productId, MultipartFile file) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with id:" + productId + " not found."));
        String filename = fileSystemRepository.saveProductFile(file);

        product.setFilename(filename);
        Product savedProduct = productRepository.save(product);

        return new PatchProductResponse(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getPrice(),
                savedProduct.getDescription(),
                savedProduct.getBrandName(),
                savedProduct.getAvailableQuantityLeft(),
                savedProduct.getProductCategory(),
                savedProduct.getSeller().getId(),
                savedProduct.getPublishedAtDate(),
                savedProduct.getWithdrewAtDate(),
                savedProduct.isPublishedStatus(),
                savedProduct.getSoldNumber(),
                savedProduct.getFilename()
        );
    }

    @Override
    public FileSystemResource getProductImage(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with id:" + productId + " not found."));
        try {
            return fileSystemRepository.findUploadedFileByFilename(product.getFilename());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
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
