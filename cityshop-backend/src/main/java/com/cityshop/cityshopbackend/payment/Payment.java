package com.cityshop.cityshopbackend.payment;

import com.cityshop.cityshopbackend.shop_payment.entity.ShopPayment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_amount")
    private Long totalAmount;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String status = "PENDING";

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ShopPayment> shopPayments = new HashSet<>();

    public void addShopPayment(ShopPayment shopPayment) {
        shopPayments.add(shopPayment);
    }
}
