package com.cityshop.cityshopbackend.shopkeeper;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopkeeperRepository extends JpaRepository<Shopkeeper, Integer> {
    Optional<Shopkeeper> findByEmail(String email);
}
