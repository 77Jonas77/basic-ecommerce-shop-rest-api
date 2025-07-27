package dev.jsojka.basic_ecommerce_shop.products;

import dev.jsojka.basic_ecommerce_shop.users.UserEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "product", schema = "product_schema")
public class ProductEntity {

    @Id
    @Column(name = "product_id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "brand_name", nullable = false)
    private String brandName; // could be impl as Class or smth

    @Column(name = "available_quantity_left", nullable = false)
    private int availableQuantityLeft;

    @Column(name = "category", nullable = false)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "seller_user_id", nullable = false)
    private UserEntity seller;

    @Column(name = "published_at_date", nullable = true)
    private LocalDateTime publishedAtDate;

    @Column(name = "withdrew_at_date", nullable = true)
    private LocalDateTime withdrewAtDate; // TIMESTAMP WITHOUT TIME ZONE IN POSTGRESQL

    @Column(name = "published_status", nullable = false)
    private boolean publishedStatus;

    @Column(name = "sold_number", nullable = false)
    private int soldNumber;

    public ProductEntity() {
    }

    public ProductEntity(UUID id, String name, BigDecimal price, String description, String brandName,
                         int availableQuantityLeft, ProductCategory productCategory, UserEntity seller) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.brandName = brandName;
        this.availableQuantityLeft = availableQuantityLeft;
        this.productCategory = productCategory;
        this.seller = seller;
        this.publishedStatus = false;
        this.soldNumber = 0;
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

    public UserEntity getSeller() {
        return seller;
    }

    public void setSeller(UserEntity seller) {
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
}
