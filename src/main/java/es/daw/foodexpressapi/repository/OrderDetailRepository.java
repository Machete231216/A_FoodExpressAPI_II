package es.daw.foodexpressapi.repository;

import es.daw.foodexpressapi.entity.OrderDetail;
import es.daw.foodexpressapi.entity.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
}
