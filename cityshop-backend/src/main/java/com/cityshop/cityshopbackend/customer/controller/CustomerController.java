package com.cityshop.cityshopbackend.customer.controller;

import com.cityshop.cityshopbackend.customer.Customer;
import com.cityshop.cityshopbackend.customer.service.CustomerService;
import com.cityshop.cityshopbackend.shop.Shop;
import com.cityshop.cityshopbackend.shop.service.ShopService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cityshop/customer")
public class CustomerController {

    private final ShopService shopService;
    private final CustomerService customerService;

    public CustomerController(ShopService shopService, CustomerService customerService) {
        this.shopService = shopService;
        this.customerService = customerService;
    }

    @GetMapping("/shops")
    public List<Shop> dashboard() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        assert authentication != null;
        Long customerId = (Long) authentication.getPrincipal();

        Customer customer = customerService.getCustomersById(customerId);

        return shopService.getAllShops(customer.getCity().getCityName());
    }
}
