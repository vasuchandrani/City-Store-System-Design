package com.cityshop.cityshopbackend.shopkeeper;

import com.cityshop.cityshopbackend.shop.Shop;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "shopkeeper")
public class Shopkeeper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String password;

    @Column(name = "phone_no")
    private String phoneNumber;

    @Column(name = "upi_id")
    private String upiId;

    @Column(name = "gst_no")
    private String gstNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public String getRole() {
        return "SHOPKEEPER";
    }
}