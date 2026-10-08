package com.nexoventas.api.sale;
import com.nexoventas.api.customer.*;
import com.nexoventas.api.product.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
@Service
public class SaleService {
    private final SaleRepository sales; private final CustomerRepository customers; private final ProductRepository products;
    public SaleService(SaleRepository sales, CustomerRepository customers, ProductRepository products) { this.sales=sales; this.customers=customers; this.products=products; }
    @Transactional public Sale create(SaleRequest request) {
        Customer customer = customers.findById(request.customerId()).orElseThrow(() -> new NoSuchElementException("Cliente no encontrado"));
        Sale sale = new Sale(customer);
        for (SaleRequest.SaleLineRequest line : request.items()) {
            Product product = products.findById(line.productId()).orElseThrow(() -> new NoSuchElementException("Producto no encontrado"));
            product.decreaseStock(line.quantity()); sale.addItem(new SaleItem(product, line.quantity()));
        }
        return sales.save(sale);
    }
}
