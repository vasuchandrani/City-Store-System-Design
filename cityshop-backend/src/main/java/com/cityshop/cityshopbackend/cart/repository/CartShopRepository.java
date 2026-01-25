package com.cityshop.cityshopbackend.cart.repository;

import com.cityshop.cityshopbackend.cart.entity.Cart;
import com.cityshop.cityshopbackend.cart.entity.CartShop;
import com.cityshop.cityshopbackend.product.Product;
import com.cityshop.cityshopbackend.shop.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartShopRepository extends JpaRepository<CartShop, Long> {

    Optional<CartShop> findByCartAndProductAndShop(Cart cart, Product product, Shop shop);

    List<CartShop> findAllByCart(Cart cart);

    List<CartShop> findAllByCart_IdAndShop_Id(Long cartId, Long shopId);
}
