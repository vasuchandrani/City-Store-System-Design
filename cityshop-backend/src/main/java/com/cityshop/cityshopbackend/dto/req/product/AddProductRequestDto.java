package com.cityshop.cityshopbackend.dto.req.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductRequestDto {

    @NotBlank(message = "Product name is required")
    private String name;

    private String description;

    @NotBlank(message = "Enter the category of the product")
    private String category;

    @Min(value = 0, message = "Price cannot be negative")
    private Long price;

    @Min(value = 0, message = "Stock cannot be negative")
    private int stock;

    @Min(value = 0, message = "Low stock threshold cannot be negative")
    private int lowStock;

    private String imageUrl;
}