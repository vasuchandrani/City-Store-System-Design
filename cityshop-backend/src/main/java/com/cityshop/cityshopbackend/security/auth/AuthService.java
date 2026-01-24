package com.cityshop.cityshopbackend.security.auth;

import com.cityshop.cityshopbackend.customer.service.CustomerAuth;
import com.cityshop.cityshopbackend.deliverer.service.DelivererAuth;
import com.cityshop.cityshopbackend.dto.req.LoginRequestDto;
import com.cityshop.cityshopbackend.dto.req.SignupRequestDto;
import com.cityshop.cityshopbackend.dto.req.customer.CustomerSignupDto;
import com.cityshop.cityshopbackend.dto.req.deliverer.DelivererSignupDto;
import com.cityshop.cityshopbackend.dto.req.shopkeeper.ShopkeeperSignupDto;
import com.cityshop.cityshopbackend.dto.res.AuthResponseDto;
import com.cityshop.cityshopbackend.shopkeeper.service.ShopkeeperAuth;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final CustomerAuth customerAuth;
    private final DelivererAuth delivererAuth;
    private final ShopkeeperAuth shopkeeperAuth;

    public AuthService(
            CustomerAuth customerAuth,
            DelivererAuth delivererAuth,
            ShopkeeperAuth shopkeeperAuth
    ) {
        this.customerAuth = customerAuth;
        this.delivererAuth = delivererAuth;
        this.shopkeeperAuth = shopkeeperAuth;
    }

    // store
    public AuthResponseDto store(SignupRequestDto request) {

        String role = request.getRole();

        return switch (role) {

            case "CUSTOMER" -> customerAuth.store((CustomerSignupDto) request);

            case "SHOPKEEPER" -> shopkeeperAuth.store((ShopkeeperSignupDto) request);

            case "DELIVERER" -> delivererAuth.store((DelivererSignupDto) request);

            default -> throw new IllegalStateException("Unexpected value: " + role);
        };
    }

    // login
    public AuthResponseDto authenticate(LoginRequestDto request) {

        String role = request.getRole();

        return switch (role) {

            case "CUSTOMER" -> customerAuth.authenticate(request);

            case "SHOPKEEPER" -> shopkeeperAuth.authenticate(request);

            case "DELIVERER" -> delivererAuth.authenticate(request);

            default -> throw new IllegalStateException("Unexpected value: " + role);
        };
    }
}

