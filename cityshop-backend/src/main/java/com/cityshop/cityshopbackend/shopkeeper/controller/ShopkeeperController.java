package com.cityshop.cityshopbackend.shopkeeper.controller;

import com.cityshop.cityshopbackend.dto.req.product.AddProductRequestDto;
import com.cityshop.cityshopbackend.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cityshop/my-shop")
public class ShopkeeperController {

    private final ProductService productService;

    public ShopkeeperController(ProductService productService) {
        this.productService = productService;
    }

    // add and remove products
    @PostMapping("/product/add")
    public boolean addProduct(@RequestBody AddProductRequestDto request, @PathVariable Long shopId) {
        return productService.addProduct(request, shopId);
    }
    @PostMapping("/product/remove")
    public boolean removeProduct(@RequestBody Long productId, @PathVariable String shopId) {
        return productService.removeProduct(productId, shopId);
    }
}