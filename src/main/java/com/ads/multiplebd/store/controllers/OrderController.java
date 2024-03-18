package com.ads.multiplebd.store.controllers;

import com.ads.multiplebd.store.dto.OrderDTO;
import com.ads.multiplebd.store.services.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {
    private IOrderService orderService;


    @GetMapping()
    public ResponseEntity<Page<OrderDTO>> getAllTOrders(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "3") int size) {
        try {
            return new ResponseEntity<>(orderService.findAllOrders(page, size), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findOrderById(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        try {
            return new ResponseEntity<>(orderService.createOrder(orderDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PutMapping()
    public ResponseEntity<OrderDTO> updateOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO order = orderService.updateOrder(orderDTO);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteOrderById(@PathVariable("id") long id) {

        try {
            orderService.deleteOrderById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteAllOrders() {
        try {
            orderService.deleteAllOrders();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/dateCreated")
    public ResponseEntity<List<OrderDTO>> findOrdersByDateCreated(@RequestParam(required = false) Date dateCreated) {
        try {
            return new ResponseEntity<>(orderService.findByDateCreated(dateCreated), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/categoryName")
    public ResponseEntity<List<OrderDTO>> findOrdersByCategoryName(@RequestParam(required = true) String categoryName) {
        try {
            return new ResponseEntity<>(orderService.findOrdersByCategoryName(categoryName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/productName")
    public ResponseEntity<List<OrderDTO>> findOrdersByProductsName(@RequestParam(required = true) String productName){
        try {
            return new ResponseEntity<>(orderService.findByProductsName(productName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/totalPriceGreaterThan")
    public ResponseEntity<List<OrderDTO>> findByTotalPriceGreaterThan(@RequestParam(required = true) BigDecimal amount){
        try {
            return new ResponseEntity<>(orderService.findByTotalPriceGreaterThan(amount), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/totalPriceLessThan")
    public ResponseEntity<List<OrderDTO>> findByTotalPriceLessThan(@RequestParam(required = true) BigDecimal amount){
        try {
            return new ResponseEntity<>(orderService.findByTotalPriceLessThan(amount), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/statusNumbers")
    public ResponseEntity<Map<String, Long>> countOrdersByStatus(@RequestParam(required = true) String status){
        try {
            return new ResponseEntity<>(orderService.countOrdersByStatus(status), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/status")
    public ResponseEntity<List<OrderDTO>> findOrdersByStatus(@RequestParam(required = true) String status){
        try {
            return new ResponseEntity<>(orderService.findByStatus(status), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderDTO>> findOrderByCustomerId(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(orderService.findByCustomerId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
