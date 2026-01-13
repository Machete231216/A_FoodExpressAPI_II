package es.daw.foodexpressapi.enums;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum DishCategory {
    HAMBURGUESAS("Hamburguesas",new BigDecimal("0.10")),
    PASTA("Pasta", new BigDecimal("0.05")),
    SUSHI("Sushi", new BigDecimal("0.15")),
    ENTRANTE("Entrante", new BigDecimal("0.00")),
    POSTRE("Postre", new BigDecimal("0.03"));

    private final String label;
    private final BigDecimal plus;

    DishCategory(String label,  BigDecimal plus) {
        this.label = label;
        this.plus = plus;
    }

    public BigDecimal applyPlus(BigDecimal basePrice){
        if (basePrice == null) return null;

        return basePrice.add(basePrice.multiply(plus));



    }
}
