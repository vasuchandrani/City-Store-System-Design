package com.cityshop.cityshopbackend.customer.service;

import com.cityshop.cityshopbackend.city.service.CityService;
import com.cityshop.cityshopbackend.customer.Customer;
import com.cityshop.cityshopbackend.customer.CustomerRepository;
import com.cityshop.cityshopbackend.dto.req.LoginRequestDto;
import com.cityshop.cityshopbackend.dto.req.customer.CustomerSignupDto;
import com.cityshop.cityshopbackend.dto.res.AuthResponseDto;
import com.cityshop.cityshopbackend.security.jwt.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerAuth {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;
    private final CityService cityService;

    public CustomerAuth(
            JwtTokenProvider jwtTokenProvider,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            CustomerRepository customerRepository,
            CityService cityService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.customerRepository = customerRepository;
        this.cityService = cityService;
    }

    public AuthResponseDto store(CustomerSignupDto request) {

        // create customer
        Customer customer = new Customer();
        customer.setEmail(request.getEmail());
        customer.setFullName(request.getFullName());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setAddress(request.getAddress());

        customer.setCity(cityService.getCityByName(request.getCityName()));

        // save in db
        Customer savedCustomer = customerRepository.save(customer);

        // generate jwt-token
        String token = jwtTokenProvider.generateToken(
                savedCustomer.getId(),
                savedCustomer.getRole()
        );

        return new AuthResponseDto(
                token,
                savedCustomer.getRole(),
                "/customer/dashboard"
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

        Customer customer = customerRepository.findByEmail(request.getEmail())
                .orElseThrow();

        // generate jwt-token
        String token = jwtTokenProvider.generateToken(
                customer.getId(),
                customer.getRole()
        );

        return new AuthResponseDto(
                token,
                customer.getRole(),
                "/customer/dashboard"
        );
    }
}
