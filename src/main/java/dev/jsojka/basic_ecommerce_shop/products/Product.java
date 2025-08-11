package dev.jsojka.basic_ecommerce_shop.products;

import dev.jsojka.basic_ecommerce_shop.users.User;
import dev.jsojka.basic_ecommerce_shop.users.UserEntity;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Product {

    @NotNull
    private UUID id;

    @NotBlank
    private String name;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @NotBlank
    private String description;

    @NotBlank
    private String brandName;

    @Min(0)
    private int availableQuantityLeft;

    @NotNull
    private ProductCategory productCategory;

    @NotNull
    private User seller;

    private LocalDateTime publishedAtDate;

    private LocalDateTime withdrewAtDate;

    @NotNull
    private boolean publishedStatus;

    @NotNull
    private int soldNumber;

    private String filename;

    public Product() {
    }

    public Product(UUID id, String name, BigDecimal price, String description, String brandName,
                   int availableQuantityLeft, ProductCategory productCategory, User seller, int soldNumber,
                   boolean publishedStatus) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.brandName = brandName;
        this.availableQuantityLeft = availableQuantityLeft;
        this.productCategory = productCategory;
        this.seller = seller;
        this.soldNumber = soldNumber;
        this.publishedStatus = publishedStatus;
    }

    public Product(UUID id, String name, BigDecimal price, String description, String brandName, int availableQuantityLeft,
                   ProductCategory productCategory, User seller, LocalDateTime publishedAtDate,
                   LocalDateTime withdrewAtDate, boolean publishedStatus, int soldNumber) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.brandName = brandName;
        this.availableQuantityLeft = availableQuantityLeft;
        this.productCategory = productCategory;
        this.seller = seller;
        this.publishedAtDate = publishedAtDate;
        this.withdrewAtDate = withdrewAtDate;
        this.publishedStatus = publishedStatus;
        this.soldNumber = soldNumber;
    }

    public Product(UUID id, String name, BigDecimal price, String description, String brandName, int availableQuantityLeft,
                   ProductCategory productCategory, UserEntity seller, LocalDateTime publishedAtDate,
                   LocalDateTime withdrewAtDate, boolean publishedStatus, int soldNumber, String filePath) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.brandName = brandName;
        this.availableQuantityLeft = availableQuantityLeft;
        this.productCategory = productCategory;
        this.seller = new User(
                seller.getId(),
                seller.getName(),
                seller.getLastName(),
                seller.getEmail(),
                seller.getRole()
        );
        this.publishedAtDate = publishedAtDate;
        this.withdrewAtDate = withdrewAtDate;
        this.publishedStatus = publishedStatus;
        this.soldNumber = soldNumber;
        this.filename = filePath;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getAvailableQuantityLeft() {
        return availableQuantityLeft;
    }

    public void setAvailableQuantityLeft(int availableQuantityLeft) {
        this.availableQuantityLeft = availableQuantityLeft;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public LocalDateTime getPublishedAtDate() {
        return publishedAtDate;
    }

    public void setPublishedAtDate(LocalDateTime publishedAtDate) {
        this.publishedAtDate = publishedAtDate;
    }

    public LocalDateTime getWithdrewAtDate() {
        return withdrewAtDate;
    }

    public void setWithdrewAtDate(LocalDateTime withdrewAtDate) {
        this.withdrewAtDate = withdrewAtDate;
    }

    public boolean isPublishedStatus() {
        return publishedStatus;
    }

    public void setPublishedStatus(boolean publishedStatus) {
        this.publishedStatus = publishedStatus;
    }

    public int getSoldNumber() {
        return soldNumber;
    }

    public void setSoldNumber(int soldNumber) {
        this.soldNumber = soldNumber;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
