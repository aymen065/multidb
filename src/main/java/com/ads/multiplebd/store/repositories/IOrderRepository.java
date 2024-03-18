package com.ads.multiplebd.store.repositories;

import com.ads.multiplebd.store.dto.OrderDTO;
import com.ads.multiplebd.store.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface IOrderRepository extends JpaRepository<Order, Long>{
    List<Order> findByDateCreated(Date dateCreated);



    @Query("SELECT new com.ads.multiplebd.store.dto.OrderDTO(o.id, o.totalQuantity, o.totalPrice, o.products) " +
            "FROM Order o JOIN o.products p JOIN p.category c " +
            "WHERE c.name = :categoryName")
    List<OrderDTO> findOrdersByCategoryName(String categoryName);
    List<Order> findByProductsName(String productName);
    List<Order> findByTotalPriceGreaterThan(BigDecimal amount);
    List<Order> findByTotalPriceLessThan(BigDecimal amount);
    Map<String, Long> countOrdersByStatus(String status);
    List<Order> findByStatus(String status);
    List<Order> findByCustomerId(Long customerId);


}
