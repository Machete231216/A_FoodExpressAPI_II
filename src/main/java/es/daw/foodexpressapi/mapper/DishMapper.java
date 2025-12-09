package es.daw.foodexpressapi.mapper;

import es.daw.foodexpressapi.dto.DishDTO;
import es.daw.foodexpressapi.dto.DishResponseDTO;
import es.daw.foodexpressapi.entity.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishMapper {

    private final RestaurantMapper restaurantMapper;

    public DishDTO toDTO(Dish dish) {
        if (dish == null) return null;

        DishDTO dto = new DishDTO();
        dto.setName(dish.getName());
        dto.setPrice(dish.getPrice());
        dto.setCategory(dish.getCategory());

        dto.setRestaurantName(
                dish.getRestaurant() != null ? dish.getRestaurant().getName() : null
        );

        return dto;
    }

    // Pendiente!!! trabajar con optional para evitar nulos...
    public DishResponseDTO toResponseDTO(Dish dish) {
        if (dish == null) return null;

        return DishResponseDTO.builder()
                .id(dish.getId())
                .name(dish.getName())
                .price(dish.getPrice())
                .category(dish.getCategory())
                .restaurant(restaurantMapper.toDTO(dish.getRestaurant()))
                .build();
    }



}


