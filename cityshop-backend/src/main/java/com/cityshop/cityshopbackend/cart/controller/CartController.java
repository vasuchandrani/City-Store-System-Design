package com.cityshop.cityshopbackend.cart.controller;

import com.cityshop.cityshopbackend.cart.entity.Cart;
import com.cityshop.cityshopbackend.cart.service.CartService;
import com.cityshop.cityshopbackend.dto.req.product.AddProductCartRequestDto;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cityshop/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("")
    public Cart getCart() {
        return cartService.getCustomerCart();
    }

    @PostMapping("/add")
    public boolean addToCart(@RequestBody AddProductCartRequestDto request) {
        return cartService.addProductToCart(request);
    }

    @PostMapping("/add/{productId}")
    public void increaseQuantity(@PathVariable Long productId) {
        cartService.increaseQuantity(productId);
    }

    @PostMapping("/remove/{productId}")
    public void decreaseQuantity(@PathVariable Long productId) {
        cartService.decreaseQuantity(productId);
    }
}
