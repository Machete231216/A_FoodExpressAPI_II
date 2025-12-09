package es.daw.foodexpressapi.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DishDTO {

    private String name;

    private BigDecimal price;

    private String category;

    private String restaurantName;
}
