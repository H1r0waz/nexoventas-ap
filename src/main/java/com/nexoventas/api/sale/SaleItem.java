package com.nexoventas.api.sale;
import com.nexoventas.api.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.*;
import java.util.UUID;
@Entity @Table(name="sale_items")
public class SaleItem {
    @Id @GeneratedValue private UUID id;
    @JsonIgnore @ManyToOne(optional=false) private Sale sale;
    @ManyToOne(optional=false) private Product product;
    @Column(nullable=false) private int quantity;
    @Column(nullable=false, precision=12, scale=2) private BigDecimal unitPrice;
    @Column(nullable=false, precision=12, scale=2) private BigDecimal subtotal;
    protected SaleItem() {}
    public SaleItem(Product product, int quantity) { this.product=product; this.quantity=quantity; this.unitPrice=product.getPrice(); this.subtotal=unitPrice.multiply(BigDecimal.valueOf(quantity)); }
    void assignSale(Sale sale){this.sale=sale;} public UUID getId(){return id;} public Product getProduct(){return product;} public int getQuantity(){return quantity;} public BigDecimal getUnitPrice(){return unitPrice;} public BigDecimal getSubtotal(){return subtotal;}
}
