package com.nexoventas.api.sale;
import com.nexoventas.api.customer.Customer;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;
@Entity @Table(name="sales")
public class Sale {
    @Id @GeneratedValue private UUID id;
    @ManyToOne(optional=false) private Customer customer;
    @Column(nullable=false) private OffsetDateTime createdAt;
    @Column(nullable=false, precision=12, scale=2) private BigDecimal total;
    @OneToMany(mappedBy="sale", cascade=CascadeType.ALL, orphanRemoval=true) private List<SaleItem> items = new ArrayList<>();
    protected Sale() {}
    public Sale(Customer customer) { this.customer=customer; this.createdAt=OffsetDateTime.now(); this.total=BigDecimal.ZERO; }
    public void addItem(SaleItem item) { item.assignSale(this); items.add(item); total=total.add(item.getSubtotal()); }
    public UUID getId(){return id;} public Customer getCustomer(){return customer;} public OffsetDateTime getCreatedAt(){return createdAt;} public BigDecimal getTotal(){return total;} public List<SaleItem> getItems(){return List.copyOf(items);}
}
