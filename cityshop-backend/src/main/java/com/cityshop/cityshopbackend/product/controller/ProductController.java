package com.cityshop.cityshopbackend.product.controller;

import com.cityshop.cityshopbackend.product.Product;
import com.cityshop.cityshopbackend.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cityshop/shops/{shopId}/products")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts(@PathVariable("shopId") Long shopId) {
        return productService.getAllProducts(shopId);
    }

    @GetMapping("/by-category")
    public List<Product> getProductsByCategory(@PathVariable Long shopId, @RequestParam String category) {
        return productService.getProductsByCategory(shopId, category);
    }

    @GetMapping("/search")
    public List<Product> search(@PathVariable Long shopId, @RequestParam String q) {
        return productService.searchProducts(shopId, q);
    }
}
