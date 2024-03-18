package com.ads.multiplebd.store.dto;

import com.ads.multiplebd.store.models.Customer;
import com.ads.multiplebd.store.models.Product;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {
    private Long id;
    private Integer totalQuantity;
    private BigDecimal totalPrice;
    private String status;
    private Date dateCreated;
    private Date lastUpdated;
    private Set<Product> products;
    private Customer customer;
    public OrderDTO(Long id, Integer totalQuantity, BigDecimal totalPrice) {
        this.id = id;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }

    public OrderDTO(Long id, Integer totalQuantity, BigDecimal totalPrice, Set<Product> products) {
        this.id = id;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.products = products;
    }
}
