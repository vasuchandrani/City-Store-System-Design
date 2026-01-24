package com.cityshop.cityshopbackend.shop;

import com.cityshop.cityshopbackend.city.City;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text", nullable = false)
    private String address;

    @Column(nullable = false)
    private String category;

    @Column(name = "banner_url", columnDefinition = "text")
    private String bannerUrl;

    @Column(name = "is_active")
    private Boolean isActive = false;

    @Column(name = "is_open")
    private Boolean isOpen = false;

    @Column(name = "is_verified")
    private Boolean isVerified = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
