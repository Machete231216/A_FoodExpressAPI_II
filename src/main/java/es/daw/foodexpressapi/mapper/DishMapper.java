package es.daw.foodexpressapi.mapper;

import es.daw.foodexpressapi.dto.DishRequestDTO;
import es.daw.foodexpressapi.dto.DishResponseDTO;
import es.daw.foodexpressapi.entity.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DishMapper {

    private final RestaurantMapper restaurantMapper;

    public DishRequestDTO toDTO(Dish dish) {
        if (dish == null) return null;

        DishRequestDTO dto = new DishRequestDTO();
        dto.setName(dish.getName());
        dto.setPrice(dish.getPrice());
        //dto.setCategory(dish.getCategory());
        //dto.setCategory(dish.getCategory().getLabel());
        dto.setCategory(dish.getCategory().name());

        dto.setRestaurantName(
                dish.getRestaurant() != null ? dish.getRestaurant().getName() : null
        );

        return dto;
    }

    // Pendiente!!! trabajar con optional para evitar nulos...
    public DishResponseDTO toResponseDTO(Dish dish) {
        if (dish == null) return null;

        BigDecimal basePrice = dish.getPrice(); // precio de la BD
        BigDecimal finalPrice = (dish.getCategory() != null) ?
                dish.getCategory().applyPlus(basePrice) : basePrice;


        return DishResponseDTO.builder()
                .id(dish.getId())
                .name(dish.getName())
                //.price(dish.getPrice())
                .price(finalPrice) // con plus
                .basePrice(basePrice)
                //.category(dish.getCategory())
                .category(dish.getCategory().name())
                .restaurant(restaurantMapper.toDTO(dish.getRestaurant()))
                .build();
    }



}


