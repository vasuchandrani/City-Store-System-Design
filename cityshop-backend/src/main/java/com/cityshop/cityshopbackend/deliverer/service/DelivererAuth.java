package com.cityshop.cityshopbackend.deliverer.service;

import com.cityshop.cityshopbackend.city.service.CityService;
import com.cityshop.cityshopbackend.deliverer.Deliverer;
import com.cityshop.cityshopbackend.deliverer.DelivererRepository;
import com.cityshop.cityshopbackend.dto.req.LoginRequestDto;
import com.cityshop.cityshopbackend.dto.req.deliverer.DelivererSignupDto;
import com.cityshop.cityshopbackend.dto.res.AuthResponseDto;
import com.cityshop.cityshopbackend.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DelivererAuth {

    private final AuthenticationManager authenticationManager;
    private final DelivererRepository delivererRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final CityService cityService;
    private final PasswordEncoder passwordEncoder;

    public DelivererAuth(AuthenticationManager authenticationManager, DelivererRepository delivererRepository, JwtTokenProvider jwtTokenProvider, CityService cityService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.delivererRepository = delivererRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.cityService = cityService;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponseDto store(DelivererSignupDto request) {

        // create deliverer
        Deliverer deliverer = new Deliverer();
        deliverer.setEmail(request.getEmail());
        deliverer.setFullName(request.getFullName());
        deliverer.setPassword(passwordEncoder.encode(request.getPassword()));
        deliverer.setPhoneNo(request.getPhoneNumber());
        deliverer.setUpiId(request.getUpiId());
        deliverer.setVehicleDetail(request.getVehicleDetail());
        deliverer.setCity(cityService.getCityByName(request.getCityName()));
        // save in db
        Deliverer savedDeliverer = delivererRepository.save(deliverer);

        // generate jwt-token
        String token = jwtTokenProvider.generateToken(
                savedDeliverer.getId(),
                savedDeliverer.getRole()
        );

        return new AuthResponseDto(
                token,
                deliverer.getRole(),
                "/deliverer/dashboard"
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

        Deliverer deliverer = delivererRepository.findByEmail(request.getEmail())
                .orElseThrow();

        // generate jwt-token
        String token = jwtTokenProvider.generateToken(
                deliverer.getId(),
                deliverer.getRole()
        );

        return new AuthResponseDto(
                token,
                deliverer.getRole(),
                "/deliverer/dashboard"
        );
    }
}
