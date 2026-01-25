package com.cityshop.cityshopbackend.order.entity;

import com.cityshop.cityshopbackend.cart.entity.Cart;
import com.cityshop.cityshopbackend.customer.Customer;
import com.cityshop.cityshopbackend.deliverer.Deliverer;
import com.cityshop.cityshopbackend.payment.Payment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String status = "PENDING";

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "deliverer_id", nullable = true)
    private Deliverer deliverer;
}