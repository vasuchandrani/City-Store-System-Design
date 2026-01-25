package com.cityshop.cityshopbackend.product.service;

import com.cityshop.cityshopbackend.dto.req.product.AddProductRequestDto;
import com.cityshop.cityshopbackend.product.Product;
import com.cityshop.cityshopbackend.product.ProductRepository;
import com.cityshop.cityshopbackend.shop.Shop;
import com.cityshop.cityshopbackend.shop.service.ShopService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ShopService shopService;

    public ProductService(ProductRepository productRepository, ShopService shopService) {
        this.productRepository = productRepository;
        this.shopService = shopService;
    }

    public boolean addProduct(AddProductRequestDto request, Long shopId) {
        try {

            // check shopkeeper authentication

            Product product = new Product();
            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setCategory(request.getCategory());
            product.setPrice(request.getPrice());
            product.setStock(request.getStock());
            product.setLowStock(request.getLowStock());
            product.setImageUrl(request.getImageUrl());

            Shop shop = shopService.getShopById(shopId);
            product.setShop(shop);

            productRepository.save(product);
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean removeProduct(Long productId, String shopId) {
        try {

            // check shopkeeper authentication

            productRepository.deleteById(productId);
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // get all products from shop
    public List<Product> getAllProducts(Long shopId) {
        return productRepository.findAllByShop(shopService.getShopById(shopId));
    }

    public Optional<Product> getProductsById(Long productId) {
        return productRepository.findById(productId);
    }

    // filter product by category
    public List<Product> getProductsByCategory(Long shopId, String category) {
        if (shopId == null) {
            throw new IllegalArgumentException("ShopId is required");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category must not be empty");
        }
        return productRepository.findByShopIdAndCategoryIgnoreCase(shopId, category.trim());
    }

    // search product by query
    public List<Product> searchProducts(Long shopId, String query) {
        return productRepository.searchByNamePrefix(
                shopId,
                query.trim()
        );
    }
}
