package com.cityshop.cityshopbackend.cart.service;

import com.cityshop.cityshopbackend.cart.entity.Cart;
import com.cityshop.cityshopbackend.cart.entity.CartShop;
import com.cityshop.cityshopbackend.cart.entity.id.CartShopId;
import com.cityshop.cityshopbackend.cart.repository.CartRepository;
import com.cityshop.cityshopbackend.cart.repository.CartShopRepository;
import com.cityshop.cityshopbackend.customer.Customer;
import com.cityshop.cityshopbackend.customer.service.CustomerService;
import com.cityshop.cityshopbackend.dto.req.product.AddProductCartRequestDto;
import com.cityshop.cityshopbackend.product.Product;
import com.cityshop.cityshopbackend.product.service.ProductService;
import com.cityshop.cityshopbackend.shop.Shop;
import com.cityshop.cityshopbackend.shop.service.ShopService;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartShopRepository cartShopRepository;
    private final ProductService productService;
    private final CustomerService customerService;

    public CartService(
            CartRepository cartRepository,
            CartShopRepository cartShopRepository,
            ProductService productService,
            CustomerService customerService
    ) {
        this.cartRepository = cartRepository;
        this.cartShopRepository = cartShopRepository;
        this.productService = productService;
        this.customerService = customerService;
    }

    public boolean addProductToCart(AddProductCartRequestDto request) {
        try {
            // find customer cart
            Cart cart = getCustomerCart();

            // find product
            Product product = productService
                    .getProductsById(request.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            Shop shop = product.getShop();


            CartShop cartShop = cartShopRepository
                    .findByCartAndProductAndShop(cart, product, shop)
                    .map(existing -> {
                        existing.setQuantity(existing.getQuantity() + 1);
                        return existing;
                    })
                    .orElseGet(() -> {
                        CartShop cs = new CartShop();
                        cs.setCart(cart);
                        cs.setShop(shop);
                        cs.setProduct(product);
                        cs.setQuantity(1);

                        cs.setId(new CartShopId(
                                cart.getId(),
                                shop.getId(),
                                product.getId()
                        ));

                        cart.getCartItems().add(cs);
                        return cs;
                    });

            cart.getCartItems().add(cartShop);
            cart.setTotalAmount((long) (cart.getTotalAmount() + product.getPrice()));

            cartRepository.save(cart);
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Cart getCustomerCart() {

        Long customerId = customerService.getCurrentCustomerId();

        Customer customer = customerService.getCustomersById(customerId);

        return cartRepository.findByCustomer(customer)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setCustomer(customer);
                    newCart.setTotalAmount(0L);
                    return cartRepository.save(newCart);
                });
    }

    public void increaseQuantity(Long productId) {

        Long customerId = customerService.getCurrentCustomerId();
        Customer customer = customerService.getCustomersById(customerId);

        Cart cart = cartRepository.findByCustomer(customer)
                .orElseThrow(() -> new IllegalStateException("Cart not found"));

        Product product = productService.getProductsById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Shop shop = product.getShop();

        CartShop cartShop = cartShopRepository
                .findByCartAndProductAndShop(cart, product, shop)
                .orElseGet(() -> {
                    CartShop cs = new CartShop();
                    cs.setId(new CartShopId(
                            cart.getId(),
                            shop.getId(),
                            product.getId()
                    ));
                    cs.setCart(cart);
                    cs.setShop(shop);
                    cs.setProduct(product);
                    cs.setQuantity(0);
                    cart.getCartItems().add(cs);
                    return cs;
                });

        cartShop.setQuantity(cartShop.getQuantity() + 1);
        cartShopRepository.save(cartShop);
    }

    public void decreaseQuantity(Long productId) {

        Long customerId = customerService.getCurrentCustomerId();
        Customer customer = customerService.getCustomersById(customerId);

        Cart cart = cartRepository.findByCustomer(customer)
                .orElseThrow(() -> new IllegalStateException("Cart not found"));

        Product product = productService.getProductsById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Shop shop = product.getShop();

        CartShop cartShop = cartShopRepository
                .findByCartAndProductAndShop(cart, product, shop)
                .orElseThrow(() -> new IllegalStateException("Product not in cart"));

        int newQty = cartShop.getQuantity() - 1;

        if (newQty <= 0) {
            // REMOVE COMPLETELY
            cart.getCartItems().remove(cartShop);
            cartShopRepository.delete(cartShop);
        } else {
            cartShop.setQuantity(newQty);
            cartShopRepository.save(cartShop);
        }
    }

    public Cart getCartById(Long cartId) {
        return  cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalStateException("Cart not found"));
    }
}
