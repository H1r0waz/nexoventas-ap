package com.nexoventas.api.sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import java.util.UUID;
import java.util.List;
public interface SaleRepository extends JpaRepository<Sale, UUID> {
    @EntityGraph(attributePaths = {"customer", "items", "items.product"})
    List<Sale> findAllBy();
}
