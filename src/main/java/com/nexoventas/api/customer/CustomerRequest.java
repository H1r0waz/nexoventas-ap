package com.nexoventas.api.customer;
import jakarta.validation.constraints.*;
public record CustomerRequest(@NotBlank String name, @Size(max=20) String taxId, @Email String email, @Size(max=30) String phone) {}
