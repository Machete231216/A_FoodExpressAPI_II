package es.daw.foodexpressapi.dto;

import java.math.BigDecimal;

public record OrderDetailDTO (
        Long orderId,
        Long dishId,
        Integer quantity,
        BigDecimal subtotal
){}
