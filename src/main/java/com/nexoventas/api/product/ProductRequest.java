package com.nexoventas.api.product;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record ProductRequest(@NotBlank @Size(max=50) String sku, @NotBlank String name, String category,
    @NotNull @DecimalMin("0.01") BigDecimal price, @PositiveOrZero int stock, @PositiveOrZero int minimumStock) {}
