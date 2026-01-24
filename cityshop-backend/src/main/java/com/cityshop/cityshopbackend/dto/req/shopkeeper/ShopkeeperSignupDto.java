package com.cityshop.cityshopbackend.dto.req.shopkeeper;

import com.cityshop.cityshopbackend.dto.req.SignupRequestDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopkeeperSignupDto implements SignupRequestDto {

    @Override
    public String getRole() {
        return "SHOPKEEPER";
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

    @NotBlank(message = "UPI-id is required")
    private String upiId;

    @NotBlank(message = "GST no. is required")
    private String gstNo;

    @NotBlank(message = "Shop name is required")
    private String shopName;

    private String shopDescription;

    @NotBlank(message = "Address is required")
    private String shopAddress;

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "Select city of the Shop")
    private String cityName;
}