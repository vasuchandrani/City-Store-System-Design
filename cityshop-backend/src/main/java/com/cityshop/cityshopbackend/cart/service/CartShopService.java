package com.cityshop.cityshopbackend.cart.service;

import com.cityshop.cityshopbackend.cart.entity.Cart;
import com.cityshop.cityshopbackend.cart.entity.CartShop;
import com.cityshop.cityshopbackend.cart.entity.id.CartShopId;
import com.cityshop.cityshopbackend.cart.repository.CartShopRepository;
import com.cityshop.cityshopbackend.product.Product;
import com.cityshop.cityshopbackend.product.service.ProductService;
import com.cityshop.cityshopbackend.shop.Shop;
import com.cityshop.cityshopbackend.shop.service.ShopService;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartShopService {

    private final CartShopRepository cartShopRepository;
    private final ShopService shopService;
    private final ProductService productService;
    private final CartService cartService;

    public CartShopService(CartShopRepository cartShopRepository, ShopService shopService, ProductService productService, CartService cartService) {
        this.cartShopRepository = cartShopRepository;
        this.shopService = shopService;
        this.productService = productService;
        this.cartService = cartService;
    }

    public List<Product> getCartProducts(Long cartId, Long shopId) {

        List<CartShop> cartItems =
                cartShopRepository.findAllByCart_IdAndShop_Id(cartId, shopId);

        return cartItems.stream()
                .map(CartShop::getProduct)
                .toList();
    }

    public List<Shop> getCartShops(Long cartId) {
        Cart cart = cartService.getCartById(cartId);
        List<CartShop> cartItems = cartShopRepository.findAllByCart(cart);

        List<Shop> shops = new ArrayList<>();
        for (CartShop cartItem : cartItems) {
            shops.add(shopService.getShopById(cartItem.getShop().getId()));
        }

        return shops;
    }
}
