package com.cityshop.cityshopbackend.shopkeeper.service;

import com.cityshop.cityshopbackend.city.service.CityService;
import com.cityshop.cityshopbackend.dto.req.LoginRequestDto;
import com.cityshop.cityshopbackend.dto.req.shopkeeper.ShopkeeperSignupDto;
import com.cityshop.cityshopbackend.dto.res.AuthResponseDto;
import com.cityshop.cityshopbackend.security.jwt.JwtTokenProvider;
import com.cityshop.cityshopbackend.shop.Shop;
import com.cityshop.cityshopbackend.shop.ShopRepository;
import com.cityshop.cityshopbackend.shopkeeper.Shopkeeper;
import com.cityshop.cityshopbackend.shopkeeper.ShopkeeperRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ShopkeeperAuth {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final ShopkeeperRepository shopkeeperRepository;
    private final PasswordEncoder passwordEncoder;
    private final CityService cityService;
    private final ShopRepository shopRepository;

    public ShopkeeperAuth(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, ShopkeeperRepository shopkeeperRepository, PasswordEncoder passwordEncoder, CityService cityService, ShopRepository shopRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.shopkeeperRepository = shopkeeperRepository;
        this.passwordEncoder = passwordEncoder;
        this.cityService = cityService;
        this.shopRepository = shopRepository;
    }

    public AuthResponseDto store(ShopkeeperSignupDto request) {

        // create shop
        Shop shop = new Shop();
        shop.setName(request.getShopName());
        shop.setDescription(request.getShopDescription());
        shop.setAddress(request.getShopAddress());
        shop.setCategory(request.getCategory());
        shop.setCity(cityService.getCityByName(request.getCityName()));
        // save in db
        Shop savedShop = shopRepository.save(shop);

        // create shopkeeper
        Shopkeeper shopkeeper = new Shopkeeper();
        shopkeeper.setEmail(request.getEmail());
        shopkeeper.setFullName(request.getFullName());
        shopkeeper.setPassword(passwordEncoder.encode(request.getPassword()));
        shopkeeper.setPhoneNumber(request.getPhoneNumber());
        shopkeeper.setUpiId(request.getUpiId());
        shopkeeper.setGstNo(request.getGstNo());

        shopkeeper.setShop(savedShop);
        // save in db
        Shopkeeper savedShopkeeper = shopkeeperRepository.save(shopkeeper);

        // generate jwt-token
        String token = jwtTokenProvider.generateToken(
                savedShopkeeper.getId(),
                savedShopkeeper.getRole()
        );

        return new AuthResponseDto(
                token,
                shopkeeper.getRole(),
                "/shopkeeper/dashboard"
        );
    }

    public AuthResponseDto authenticate(LoginRequestDto request) {

        String compositeUsername = request.getRole() + ":" + request.getEmail();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        compositeUsername,
                        request.getPassword()
                )
        );

        Shopkeeper shopkeeper = shopkeeperRepository.findByEmail(request.getEmail())
                .orElseThrow();

        // generate jwt-token
        String token = jwtTokenProvider.generateToken(
                shopkeeper.getId(),
                shopkeeper.getRole()
        );

        return new AuthResponseDto(
                token,
                shopkeeper.getRole(),
                "/shopkeeper/dashboard"
        );
    }
}