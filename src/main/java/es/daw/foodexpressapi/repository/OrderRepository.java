package es.daw.foodexpressapi.repository;

import es.daw.foodexpressapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByStatus(String status);

    List<Order> findByUserId(Long userId);

    List<Order> findByRestaurantId(Long restaurantId);

    List<Order> findByStatusAndUserId(String status, Long userId);

    List<Order> findByStatusAndRestaurantId(String status, Long restaurantId);

    List<Order> findByUserIdAndRestaurantId(Long userId, Long restaurantId);

    List<Order> findByStatusAndUserIdAndRestaurantId(String status, Long userId, Long restaurantId);



}
