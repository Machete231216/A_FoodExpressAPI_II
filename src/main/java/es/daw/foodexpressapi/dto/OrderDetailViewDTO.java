package es.daw.foodexpressapi.dto;

import java.math.BigDecimal;

public record OrderDetailViewDTO(
        String dishName,
        String category, // pediente!!! trabajar con enumerados
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal subtotal
) {
}
