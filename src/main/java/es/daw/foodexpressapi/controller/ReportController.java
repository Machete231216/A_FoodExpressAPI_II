package es.daw.foodexpressapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ¿Cuánto ha gastado cada cliente?
 *
 * Gasto total por cliente (suma de subtotales).
 *
 * GET /api/reports/customers/spend
 * ¿Qué restaurantes tienen más ventas?
 *
 * Restaurantes con más pedidos (COUNT de órdenes)
 *
 * GET /api/reports/restaurants/top-by-orders
 * ¿Cuáles son los platos más vendidos?
 *
 * Platos más vendidos por unidades (SUM de quantity)
 *
 * GET /api/reports/dishes/top-by-units
 */
@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    // ¿Cuánto ha gastado cada cliente?
    @GetMapping("/customers/spend")
    public ResponseEntity<List<CustomerSpendDTO>> getCustomerSpend() {
        return ResponseEntity.ok(reportService.getCustomerSpend());
    }

    // ¿Qué restaurantes tienen más pedidos?
    @GetMapping("/restaurants/top-by-orders")
    public ResponseEntity<List<RestaurantOrdersDTO>> getTopRestaurantsByOrders() {
        return ResponseEntity.ok(reportService.getTopRestaurantsByOrders());
    }

    //¿Cuáles son los platos más vendidos?
    @GetMapping("/dishes/top-by-units")
    public ResponseEntity<List<DishUnitsSoldDTO>> getTopDishesByUnitsSold(
            @RequestParam(defaultValue = "10") int limit
    ) {
        return ResponseEntity.ok(
                reportService.getTopDishesByUnitsSold().stream().limit(limit).toList()
        );
    }

}
