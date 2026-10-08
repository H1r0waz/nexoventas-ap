package com.nexoventas.api.customer;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerRepository repository;
    public CustomerController(CustomerRepository repository) { this.repository = repository; }
    @GetMapping public List<Customer> all() { return repository.findAll(); }
    @PostMapping public ResponseEntity<Customer> create(@Valid @RequestBody CustomerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(new Customer(request.name(), request.taxId(), request.email(), request.phone())));
    }
}
