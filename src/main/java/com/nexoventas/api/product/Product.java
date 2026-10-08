package com.nexoventas.api.product;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {
    @Id @GeneratedValue private UUID id;
    @Column(nullable = false, unique = true, length = 50) private String sku;
    @Column(nullable = false) private String name;
    private String category;
    @Column(nullable = false, precision = 12, scale = 2) private BigDecimal price;
    @Column(nullable = false) private int stock;
    @Column(nullable = false) private int minimumStock;
    protected Product() {}
    public Product(String sku, String name, String category, BigDecimal price, int stock, int minimumStock) {
        this.sku = sku; this.name = name; this.category = category; this.price = price; this.stock = stock; this.minimumStock = minimumStock;
    }
    public void update(String sku, String name, String category, BigDecimal price, int stock, int minimumStock) {
        this.sku = sku; this.name = name; this.category = category; this.price = price; this.stock = stock; this.minimumStock = minimumStock;
    }
    public void decreaseStock(int quantity) { if (quantity > stock) throw new IllegalStateException("Stock insuficiente para " + name); stock -= quantity; }
    public UUID getId() { return id; } public String getSku() { return sku; } public String getName() { return name; }
    public String getCategory() { return category; } public BigDecimal getPrice() { return price; } public int getStock() { return stock; } public int getMinimumStock() { return minimumStock; }
}
