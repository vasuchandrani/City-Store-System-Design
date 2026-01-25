package com.cityshop.cityshopbackend.shop.service;

import com.cityshop.cityshopbackend.city.City;
import com.cityshop.cityshopbackend.city.service.CityService;
import com.cityshop.cityshopbackend.shop.Shop;
import com.cityshop.cityshopbackend.shop.ShopRepository;
import com.cityshop.cityshopbackend.shop_payment.ShopPaymentRepository;
import com.cityshop.cityshopbackend.shop_payment.entity.ShopPayment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    private final CityService cityService;
    private final ShopRepository shopRepository;
    private final ShopPaymentRepository shopPaymentRepository;

    public ShopService(CityService cityService, ShopRepository shopRepository, ShopPaymentRepository shopPaymentRepository) {
        this.cityService = cityService;
        this.shopRepository = shopRepository;
        this.shopPaymentRepository = shopPaymentRepository;
    }

    // get shops in the particular city
    public List<Shop> getAllShops(String cityName) {
        City city = cityService.getCityByName(cityName);
        return shopRepository.findShopByCity(city);
    }

    public Shop getShopByName(String shopName) {
        return shopRepository.findShopByName(shopName);
    }
    public Shop getShopById(Long shopId) {
        return shopRepository.findShopById(shopId);
    }

    // search shop
    public List<Shop> searchShops(String query) {
        String normalizedQuery = query.trim();
        return shopRepository.searchByNamePrefix(normalizedQuery);
    }

    // filter shops by category
    public List<Shop> getShopsByCategory(String category) {

        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category must not be empty");
        }

        return shopRepository.findByCategoryIgnoreCase(category.trim());
    }

    public ShopPayment store(ShopPayment shopPayment) {
        return shopPaymentRepository.save(shopPayment);
    }
}