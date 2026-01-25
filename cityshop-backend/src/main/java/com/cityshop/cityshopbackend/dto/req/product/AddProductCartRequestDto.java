package com.cityshop.cityshopbackend.dto.req.product;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductCartRequestDto {

    @NotBlank(message = "Select product required")
    private Long productId;
}
