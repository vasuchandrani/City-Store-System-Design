package com.cityshop.cityshopbackend.shop_payment;

import com.cityshop.cityshopbackend.shop_payment.entity.ShopPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopPaymentRepository extends JpaRepository<ShopPayment,Long> {

}
