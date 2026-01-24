package com.cityshop.cityshopbackend.deliverer;

import com.cityshop.cityshopbackend.city.City;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "delivery_partner")
public class Deliverer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String password;

    @Column(name = "phone_no", nullable = false)
    private String phoneNo;

    @Column(name = "upi_id",  nullable = false)
    private String upiId;

    @Column(name = "vehicle_detail", columnDefinition = "text", nullable = false)
    private String vehicleDetail;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public String getRole() {
        return "DELIVERER";
    }
}