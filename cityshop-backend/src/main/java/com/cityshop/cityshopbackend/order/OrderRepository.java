package com.cityshop.cityshopbackend.order;

import com.cityshop.cityshopbackend.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
