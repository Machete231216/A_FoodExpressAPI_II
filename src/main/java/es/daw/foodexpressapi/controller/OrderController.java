package es.daw.foodexpressapi.controller;

import es.daw.foodexpressapi.dto.OrderResponseDTO;
import es.daw.foodexpressapi.dto.OrderSummaryDTO;
import es.daw.foodexpressapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> filterOrders(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long restaurantId
    ) {

            return ResponseEntity.ok(orderService.filterOrders(status, userId, restaurantId));

    }

    @GetMapping("/summary")
    public ResponseEntity<List<OrderSummaryDTO>> getAllOrderSummaries(){
        return ResponseEntity.ok(orderService.getAllOrderSummaries());
    }

    // PENDIENTE CREAR UN PEDIDO!!!!!
    // Cualquier usuario con rol admin y client puede crear pedido (orders)
    // Esto es para trastear con seguridad y con transacciones...
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(){
        return null;
    }

}
