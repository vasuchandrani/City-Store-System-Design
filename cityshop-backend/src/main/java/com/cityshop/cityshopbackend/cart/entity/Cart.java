package com.cityshop.cityshopbackend.cart.entity;

import com.cityshop.cityshopbackend.customer.Customer;
import com.cityshop.cityshopbackend.product.Product;
import com.cityshop.cityshopbackend.shop.Shop;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_amount")
    private Long totalAmount;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartShop> cartItems = new HashSet<>();
}
