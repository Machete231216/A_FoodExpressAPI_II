package es.daw.foodexpressapi.service;

import es.daw.foodexpressapi.dto.OrderDetailDTO;
import es.daw.foodexpressapi.entity.OrderDetail;
import es.daw.foodexpressapi.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public List<OrderDetailDTO> findAll(){
        return orderDetailRepository.findAll()
                .stream()
                .map(od ->
                        new OrderDetailDTO(
                                od.getOrderDetailId().getOrderId(),
                                od.getOrderDetailId().getDishId(),
                                od.getQuantity(),
                                od.getSubtotal()
                        ))
                .toList();
    }

}
