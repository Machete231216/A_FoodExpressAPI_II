package es.daw.foodexpressapi.service;

import es.daw.foodexpressapi.dto.DishDTO;
import es.daw.foodexpressapi.dto.DishResponseDTO;
import es.daw.foodexpressapi.mapper.DishMapper;
import es.daw.foodexpressapi.repository.DishRepository;
import es.daw.foodexpressapi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishMapper dishMapper;

    public List<DishResponseDTO> findAll() {
    //public List<DishDTO> findAll() {
        return dishRepository.findAll()
                .stream()
                //.map(dishMapper::toDTO)
                .map(dishMapper::toResponseDTO)
                .toList();
    }
}
