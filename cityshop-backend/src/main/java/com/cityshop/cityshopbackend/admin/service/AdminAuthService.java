package com.cityshop.cityshopbackend.admin.service;

import com.cityshop.cityshopbackend.dto.req.admin.AdminLoginRequestDto;
import com.cityshop.cityshopbackend.dto.res.admin.AdminLoginResponseDto;
import com.cityshop.cityshopbackend.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthService {

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPasswordHash;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AdminAuthService(
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public AdminLoginResponseDto authenticate(AdminLoginRequestDto request) {

        if (!request.getEmail().equals(adminUsername)) {
            throw new RuntimeException("Invalid admin credentials");
        }

        if (!request.getPassword().equals(adminPasswordHash)) {
            throw new RuntimeException("Invalid admin credentials");
        }

        String token = jwtTokenProvider.generateAdminToken(
                request.getEmail(),
                "ADMIN"
        );

        return new AdminLoginResponseDto(
                token
        );
    }
}