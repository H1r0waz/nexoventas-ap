package com.nexoventas.api.sale;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/v1/sales")
public class SaleController {
    private final SaleService service; private final SaleRepository repository;
    public SaleController(SaleService service, SaleRepository repository) { this.service=service; this.repository=repository; }
    @GetMapping public List<Sale> all() { return repository.findAllBy(); }
    @PostMapping public ResponseEntity<Sale> create(@Valid @RequestBody SaleRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request)); }
}
