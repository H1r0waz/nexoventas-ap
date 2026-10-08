package com.nexoventas.api.sale;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.*;
public record SaleRequest(@NotNull UUID customerId, @NotEmpty List<@Valid SaleLineRequest> items) {
    public record SaleLineRequest(@NotNull UUID productId, @Positive int quantity) {}
}
