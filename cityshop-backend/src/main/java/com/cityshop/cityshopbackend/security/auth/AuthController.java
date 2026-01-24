package com.cityshop.cityshopbackend.security.auth;

import com.cityshop.cityshopbackend.dto.req.LoginRequestDto;
import com.cityshop.cityshopbackend.dto.req.SignupRequestDto;
import com.cityshop.cityshopbackend.dto.req.customer.CustomerSignupDto;
import com.cityshop.cityshopbackend.dto.req.deliverer.DelivererSignupDto;
import com.cityshop.cityshopbackend.dto.req.shopkeeper.ShopkeeperSignupDto;
import com.cityshop.cityshopbackend.dto.res.AuthResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cityshop/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // signup
    @PostMapping("/customer/signup")
    public AuthResponseDto customerSignup(@RequestBody CustomerSignupDto request) {
        return authService.store((SignupRequestDto) request);
    }

    @PostMapping("/shopkeeper/signup")
    public AuthResponseDto shopkeeperSignup(@RequestBody ShopkeeperSignupDto request) {
        return authService.store((SignupRequestDto) request);
    }

    @PostMapping("/deliverer/signup")
    public AuthResponseDto delivererSignup(@RequestBody DelivererSignupDto request) {
        return authService.store((SignupRequestDto) request);
    }

    // login
    @PostMapping("/customer/login")
    public AuthResponseDto customerLogin(@RequestBody LoginRequestDto request) {
        return authService.authenticate(request);
    }

    @PostMapping("/shopkeeper/login")
    public AuthResponseDto shopkeeperLogin(@RequestBody LoginRequestDto request) {
        return authService.authenticate(request);
    }

    @PostMapping("/deliverer/login")
    public AuthResponseDto delivererLogin(@RequestBody LoginRequestDto request) {
        return authService.authenticate(request);
    }
}
