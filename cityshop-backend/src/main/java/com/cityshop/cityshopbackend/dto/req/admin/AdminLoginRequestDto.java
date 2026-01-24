package com.cityshop.cityshopbackend.dto.req.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminLoginRequestDto {

    private final String email;
    private final String password;

    public AdminLoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
