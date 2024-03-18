package com.ads.multiplebd.store.services.impl;

import com.ads.multiplebd.store.dto.OrderDTO;
import com.ads.multiplebd.store.models.Order;
import com.ads.multiplebd.store.repositories.IOrderRepository;
import com.ads.multiplebd.store.services.IOrderService;
import com.ads.multiplebd.store.services.IProductService;
import lombok.AllArgsConstructor;
import org.dozer.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;


@Service
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private IOrderRepository orderRepository;
    private IProductService productService;
    private Mapper mapper;
    @Override
    public List<OrderDTO> findByDateCreated(Date dateCreated) {
        return orderRepository.findByDateCreated(dateCreated).stream().map(order -> mapper.map(order,OrderDTO.class)).toList();
    }

    @Override
    public Page<OrderDTO> findAllOrders(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        return orderRepository.findAll(paging).map(order -> mapper.map(order,OrderDTO.class));
    }

    @Override
    public List<OrderDTO> findOrdersByCategoryName(String categoryName) {
        return orderRepository.findOrdersByCategoryName(categoryName);
    }

    @Override
    public List<OrderDTO> findByProductsName(String productName) {
        return orderRepository.findByProductsName(productName).stream().map(order -> mapper.map(order,OrderDTO.class)).toList();
    }

    @Override
    public List<OrderDTO> findByTotalPriceGreaterThan(BigDecimal amount) {
        return orderRepository.findByTotalPriceGreaterThan(amount).stream().map(order -> mapper.map(order,OrderDTO.class)).toList();
    }

    @Override
    public List<OrderDTO> findByTotalPriceLessThan(BigDecimal amount) {
        return orderRepository.findByTotalPriceLessThan(amount).stream().map(order -> mapper.map(order,OrderDTO.class)).toList();
    }

    @Override
    public Map<String, Long> countOrdersByStatus(String status) {
        return orderRepository.countOrdersByStatus(status);
    }

    @Override
    public List<OrderDTO> findByStatus(String status) {
        return orderRepository.findByStatus(status).stream().map(order -> mapper.map(order,OrderDTO.class)).toList();
    }

    @Override
    public List<OrderDTO> findByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId).stream().map(order -> mapper.map(order,OrderDTO.class)).toList();

    }

    @Override
    public OrderDTO findById(Long id) {
        return mapper.map(orderRepository.findById(id)
                ,OrderDTO.class);
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {


        if(orderDTO.getProducts() != null) {
            Set<Long> ids = new HashSet<>();
            orderDTO.getProducts()
                    .forEach(
                            product -> ids.add(product.getId())
                    );
            orderDTO.setProducts(new HashSet<>(productService.findAllByIdIn(ids)));

        }

        return mapper.map(orderRepository.save(new Order(orderDTO)),OrderDTO.class);
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        if(orderDTO.getProducts() != null) {
            Set<Long> ids = new HashSet<>();
            orderDTO.getProducts()
                    .forEach(
                            product -> ids.add(product.getId())
                    );
            orderDTO.setProducts(new HashSet<>(productService.findAllByIdIn(ids)));

        }
        return mapper.map(orderRepository.save(mapper.map(orderDTO,Order.class)),OrderDTO.class);



//        return orderRepository.findById(orderDTO.getId())
//                .map(order -> {
//                    order.setStatus(orderDTO.getStatus());
//                    order.setProducts(orderDTO.getProducts());
//                    order.setTotalPrice(orderDTO.getTotalPrice());
//                    order.setTotalQuantity(orderDTO.getTotalQuantity());
//                    return mapper.map(orderRepository.save(order),OrderDTO.class);
//                }).orElse(null);

    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);

    }

    @Override
    public void deleteAllOrders() {
        orderRepository.deleteAll();

    }


}
