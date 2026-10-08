package com.nexoventas.api.product;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductRepository repository;
    public ProductController(ProductRepository repository) { this.repository = repository; }
    @GetMapping public List<Product> all() { return repository.findAll(); }
    @GetMapping("/low-stock") public List<Product> lowStock() { return repository.findAll().stream().filter(p -> p.getStock() <= p.getMinimumStock()).toList(); }
    @PostMapping public ResponseEntity<Product> create(@Valid @RequestBody ProductRequest r) {
        if (repository.existsBySku(r.sku())) throw new IllegalArgumentException("El SKU ya está registrado");
        Product saved = repository.save(new Product(r.sku(), r.name(), r.category(), r.price(), r.stock(), r.minimumStock()));
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    @PutMapping("/{id}") public Product update(@PathVariable UUID id, @Valid @RequestBody ProductRequest r) {
        Product p = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Producto no encontrado"));
        p.update(r.sku(), r.name(), r.category(), r.price(), r.stock(), r.minimumStock()); return repository.save(p);
    }
}
