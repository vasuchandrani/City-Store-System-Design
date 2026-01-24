package com.cityshop.cityshopbackend.dto.req.customer;

import com.cityshop.cityshopbackend.dto.req.SignupRequestDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerSignupDto implements SignupRequestDto {

    @Override
    public String getRole() {
        return "CUSTOMER";
    }

    @NotBlank(message = "Official college email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Phone-number is required")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Select your city first")
    private String cityName;
}