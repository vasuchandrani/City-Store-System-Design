package com.cityshop.cityshopbackend.dto.req.deliverer;

import com.cityshop.cityshopbackend.dto.req.SignupRequestDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DelivererSignupDto implements SignupRequestDto {

    @Override
    public String getRole() {
        return "DELIVERER";
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

    @NotBlank(message = "Vehicle details are required")
    private String vehicleDetail;

    @NotBlank(message = "Select your city first")
    private String cityName;
}
