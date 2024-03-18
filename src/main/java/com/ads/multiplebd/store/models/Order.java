package com.ads.multiplebd.store.models;

import com.ads.multiplebd.store.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="total_quantity")
    private Integer totalQuantity;

    @Column(name="total_price")
    private BigDecimal totalPrice;

    @Column(name="status")
    private String status;

    @Column(name="date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name="last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Set<Product> products;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    public Order(OrderDTO orderDTO) {
        this.id = orderDTO.getId();
        this.totalPrice = orderDTO.getTotalPrice();
        this.products = orderDTO.getProducts();
        this.totalQuantity = orderDTO.getTotalQuantity();
        this.dateCreated = orderDTO.getDateCreated();
        this.lastUpdated = orderDTO.getLastUpdated();
        this.status = orderDTO.getStatus();
        this.customer = orderDTO.getCustomer();
    }


}
