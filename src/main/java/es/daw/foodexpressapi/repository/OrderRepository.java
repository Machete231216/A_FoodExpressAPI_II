package es.daw.foodexpressapi.repository;

import es.daw.foodexpressapi.dto.OrderSummaryDTO;
import es.daw.foodexpressapi.entity.Order;
import es.daw.foodexpressapi.entity.OrderDetail;
import es.daw.foodexpressapi.enums.OrderStatus;
import jakarta.persistence.OneToMany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

//    List<Order> findByStatus(String status);
//
//    List<Order> findByUserId(Long userId);
//
//    List<Order> findByRestaurantId(Long restaurantId);
//
//    List<Order> findByStatusAndUserId(String status, Long userId);
//
//    List<Order> findByStatusAndRestaurantId(String status, Long restaurantId);
//
//    List<Order> findByUserIdAndRestaurantId(Long userId, Long restaurantId);
//
//    List<Order> findByStatusAndUserIdAndRestaurantId(String status, Long userId, Long restaurantId);


    @Query("""
        SELECT new es.daw.foodexpressapi.dto.OrderSummaryDTO(
            o.id,
            u.username,
            r.name,
            SUM(od.quantity),
            SUM(od.subtotal)
        )
        FROM Order o
        JOIN o.user u
        JOIN o.restaurant r
        JOIN o.orderDetails od
        GROUP BY o.id, u.username, r.name
        ORDER BY o.id
        """)
    public List<OrderSummaryDTO> findAllOrderSummaries(); //PENDIENTE// devolver OrderSummary de un solo pedido

    @Query("""
        SELECT o FROM Order o
            WHERE (:status IS NULL OR o.status = :status)
                AND (:userId IS NULL OR o.user.id = :userId)
                    AND (:restaurantId IS NULL OR o.restaurant.id = :restaurantId)
    """
    )
    public List<Order> findByFilters(
            //@Param("status") String status,
            @Param("status") OrderStatus status,
            Long userId,
            Long restaurantId
    );


    // ------------------ ENPOINTS DEL GIT HUB ----------------------
    // ¿Cuánto ha gastado cada cliente?
//        - Agrupar pedidos por usuario
//        - Sumar el subtotal de todas las líneas de pedido (OrderDetail)
//        - Devolver un ranking ordenado por gasto total
//
//        - La entidad raíz es Order.
//        - Cada fila de partida es un pedido.
//        - Desde ahí navegamos a User y a OrderDetail
//        - Order tiene una relación @OneToMany
//    con OrderDetail
//        - Cada pedido tiene varias líneas
//        - Cada línea tiene un subtotal
//        - Esto multiplica filas internamente (una por cada línea), lo cual es justo lo que necesitamos para poder sumar.
//
//            - SUM() es una función agregada
//        - Obliga al uso de GROUP BY
//
//        - u.id → no agregado → debe ir en GROUP BY
//        - u.username → no agregado → debe ir en GROUP BY
//        - SUM(od.subtotal) → agregado → NO va en GROUP BY
//
//        - Si no se pone nada, en JPQL es un INNER JOIN por defecto
//        - Solo cuentan los pedidos que tengan líneas, es decir, que tengan detalles asociados. Para tenerlo en cuenta habría que cambiar a LEFT JOIN
//        - Si quisiéramos incluir también pedidos sin líneas, habría que usar LEFT JOIN, y además tratar los valores null en la suma.
//        - SUM(COALESCE(od.subtotal, 0))
//
//            - Se hace una proyección a DTO (no se devuelven entidades)
    @Query("""
        SELECT new es.daw.foodexpressapi.dto.report.CustomerSpendDTO(
            u.id,
            u.username,
            SUM(od.subtotal)
        )
        FROM Order o
        JOIN o.user u
        JOIN o.orderDetails od
        GROUP BY u.id, u.username
        ORDER BY SUM(od.subtotal) DESC
    """)
    List<CustomerSpendDTO> findCustomerSpend();


    @Query("""
        SELECT new es.daw.foodexpressapi.dto.report.RestaurantOrdersDTO(
            r.id,
            r.name,
            COUNT(o.id)
        )
        FROM Order o
        JOIN o.restaurant r
        GROUP BY r.id, r.name
        ORDER BY COUNT(o.id) DESC
    """)
    List<RestaurantOrdersDTO> findTopRestaurantsByOrders();

    @Query("""
        SELECT new es.daw.foodexpressapi.dto.report.DishUnitsSoldDTO(
            d.id,
            d.name,
            SUM(od.quantity)
        )
        FROM Order o
        JOIN o.details od
        JOIN od.dish d
        GROUP BY d.id, d.name
        ORDER BY SUM(od.quantity) DESC
    """)
    List<DishUnitsSoldDTO> findTopDishesByUnitsSold();

}
