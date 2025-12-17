package es.daw.foodexpressapi.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record OrderDetailViewDTO(
        @NotBlank
        String dishName,
        @NotBlank(message = "lajsdlajsldfkjaldsfaksdf")
        String category, // pediente!!! trabajar con enumerados
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal subtotal
) {
}
