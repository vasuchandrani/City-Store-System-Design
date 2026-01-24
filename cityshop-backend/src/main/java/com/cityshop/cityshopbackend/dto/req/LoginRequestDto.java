package com.cityshop.cityshopbackend.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

    private final String email;
    private final String password;
    private final String role;

    public LoginRequestDto(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

}
