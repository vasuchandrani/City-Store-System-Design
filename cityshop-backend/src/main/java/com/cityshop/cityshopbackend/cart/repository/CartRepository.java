package com.cityshop.cityshopbackend.cart.repository;

import com.cityshop.cityshopbackend.cart.entity.Cart;
import com.cityshop.cityshopbackend.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByCustomer(Customer customer);

}
