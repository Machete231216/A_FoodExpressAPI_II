package es.daw.foodexpressapi.repository;

import es.daw.foodexpressapi.entity.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {

    List<Dish> findByRestaurantId(Long restaurantId);

    Page<Dish> findByRestaurantId(Long restaurantId, Pageable pageable);

}

