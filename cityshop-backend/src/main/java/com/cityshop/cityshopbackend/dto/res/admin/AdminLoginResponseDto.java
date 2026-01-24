package com.cityshop.cityshopbackend.dto.res.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminLoginResponseDto {

    private String token;

    public AdminLoginResponseDto(String token) {
        this.token = token;
    }
}
