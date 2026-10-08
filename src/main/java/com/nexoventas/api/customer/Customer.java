package com.nexoventas.api.customer;
import jakarta.persistence.*;
import java.util.UUID;
@Entity @Table(name="customers")
public class Customer {
    @Id @GeneratedValue private UUID id;
    @Column(nullable=false) private String name;
    @Column(unique=true) private String taxId;
    private String email; private String phone;
    protected Customer() {}
    public Customer(String name, String taxId, String email, String phone) { this.name=name; this.taxId=taxId; this.email=email; this.phone=phone; }
    public UUID getId(){return id;} public String getName(){return name;} public String getTaxId(){return taxId;} public String getEmail(){return email;} public String getPhone(){return phone;}
}
