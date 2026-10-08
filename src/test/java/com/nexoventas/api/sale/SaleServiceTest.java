package com.nexoventas.api.sale;
import com.nexoventas.api.customer.*;
import com.nexoventas.api.product.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaleServiceTest {
    @Test void deductsStockWhenSaleIsCreated() {
        SaleRepository sales=mock(SaleRepository.class); CustomerRepository customers=mock(CustomerRepository.class); ProductRepository products=mock(ProductRepository.class);
        Customer customer=new Customer("Comercio Demo", "20-123", "demo@example.com", "11-1234");
        Product product=new Product("TE-001", "Teclado", "Periféricos", new BigDecimal("25000"), 10, 2);
        UUID customerId=UUID.randomUUID(), productId=UUID.randomUUID();
        when(customers.findById(customerId)).thenReturn(Optional.of(customer)); when(products.findById(productId)).thenReturn(Optional.of(product));
        when(sales.save(any(Sale.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Sale result=new SaleService(sales, customers, products).create(new SaleRequest(customerId, List.of(new SaleRequest.SaleLineRequest(productId, 3))));
        assertEquals(7, product.getStock()); assertEquals(new BigDecimal("75000"), result.getTotal()); verify(sales).save(result);
    }
}
