package com.ads.multiplebd.store.services;

import com.ads.multiplebd.store.dto.OrderDTO;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface IOrderService {

    List<OrderDTO> findByDateCreated(Date dateCreated);
    Page<OrderDTO> findAllOrders(Integer page, Integer size);
    List<OrderDTO> findOrdersByCategoryName(String categoryName);
    List<OrderDTO> findByProductsName(String productName);
    List<OrderDTO> findByTotalPriceGreaterThan(BigDecimal amount);
    List<OrderDTO> findByTotalPriceLessThan(BigDecimal amount);
    Map<String, Long> countOrdersByStatus(String status);
    List<OrderDTO> findByStatus(String status);
    List<OrderDTO> findByCustomerId(Long customerId);
    OrderDTO findById(Long id);
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO updateOrder(OrderDTO orderDTO);


    void deleteOrderById(Long id);
    void deleteAllOrders();

}
