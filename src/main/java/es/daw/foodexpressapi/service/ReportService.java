package es.daw.foodexpressapi.service;

import es.daw.foodexpressapi.dto.report.CustomerSpendDTO;
import es.daw.foodexpressapi.dto.report.DishUnitsSoldDTO;
import es.daw.foodexpressapi.dto.report.RestaurantOrdersDTO;
import es.daw.foodexpressapi.repository.OrderRepository;
import es.daw.foodexpressapi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final OrderRepository orderRepository;

    private final RestaurantRepository restaurantRepository;

    public List<CustomerSpendDTO> getCustomerSpend() {
        return orderRepository.findCustomerSpend();
    }

    public List<RestaurantOrdersDTO> getTopRestaurantsByOrders() {
        return orderRepository.findTopRestaurantsByOrders();
    }

//    public List<RestaurantOrdersDTO> getTopRestaurantsByOrders(boolean allRestaurants) {
//        // o uno u otro, todo depende de si devolvemos restaurantes sin orders
//        if (allRestaurants) return restaurantRepository.findTopRestaurantsByOrders();
//        return orderRepository.findTopRestaurantsByOrders();
//    }

    public List<DishUnitsSoldDTO> getTopDishesByUnitsSold() {
        return orderRepository.findTopDishesByUnitsSold();


    }

}
