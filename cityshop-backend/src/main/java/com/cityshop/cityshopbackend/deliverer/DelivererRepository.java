package com.cityshop.cityshopbackend.deliverer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DelivererRepository extends JpaRepository<Deliverer, Long> {
    Optional<Deliverer> findByEmail(String email);
}
