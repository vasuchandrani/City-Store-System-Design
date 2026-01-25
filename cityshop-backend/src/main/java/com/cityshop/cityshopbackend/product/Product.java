package com.cityshop.cityshopbackend.product;

import com.cityshop.cityshopbackend.shop.Shop;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "price")
    private Long price;

    @Column(name = "stock")
    private int stock;

    @Column(name = "low_stock")
    private int lowStock;

    @Column(name = "stock_alert")
    private boolean stockAlert;

    @Column(name = "is_available")
    private boolean isAvailable;

    @Column(name = "sold_quantity")
    private int soldQuantity;

    @Column(name = "image_url")
    private String imageUrl;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;
}
