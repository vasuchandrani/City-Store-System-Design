package com.cityshop.cityshopbackend.shop.controller;

import com.cityshop.cityshopbackend.shop.Shop;
import com.cityshop.cityshopbackend.shop.service.ShopService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cityshop/shops")
public class ShopController {

    private final ShopService shopService;
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/shop")
    public Shop getShop(@RequestParam String shopName) {
        return shopService.getShopByName(shopName);
    }

    @GetMapping("/{shopId}")
    public Shop getShopById(@PathVariable Long shopId) {
        return shopService.getShopById(shopId);
    }

    @GetMapping("/search")
    public List<Shop> searchShops(@RequestParam String q) {
        return shopService.searchShops(q);
    }

    @GetMapping("/by-category")
    public List<Shop> getShopsByCategory(@RequestParam String category) {
        return shopService.getShopsByCategory(category);
    }
}
