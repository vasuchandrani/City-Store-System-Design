package com.cityshop.cityshopbackend.order.service;

import com.cityshop.cityshopbackend.cart.entity.Cart;
import com.cityshop.cityshopbackend.cart.service.CartService;
import com.cityshop.cityshopbackend.cart.service.CartShopService;
import com.cityshop.cityshopbackend.customer.Customer;
import com.cityshop.cityshopbackend.order.OrderRepository;
import com.cityshop.cityshopbackend.order.entity.Order;
import com.cityshop.cityshopbackend.payment.Payment;
import com.cityshop.cityshopbackend.payment.service.PaymentService;
import com.cityshop.cityshopbackend.product.Product;
import com.cityshop.cityshopbackend.shop.Shop;
import com.cityshop.cityshopbackend.shop.service.ShopService;
import com.cityshop.cityshopbackend.shop_payment.entity.ShopPayment;
import com.cityshop.cityshopbackend.shop_payment.service.ShopPaymentService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.ArrayList;

@Service
public class OrderService {

    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final CartShopService cartShopService;
    private final ShopService shopService;
    private final PaymentService paymentService;

    public OrderService(CartService cartService, OrderRepository orderRepository, CartShopService cartShopService, ShopService shopService, PaymentService paymentService) {
        this.cartService = cartService;
        this.orderRepository = orderRepository;
        this.cartShopService = cartShopService;
        this.shopService = shopService;
        this.paymentService = paymentService;
    }

    public Order placeOrder(Long cartId) {

        // get cart
        Cart cart = cartService.getCartById(cartId);
        // get customer
        Customer customer = cart.getCustomer();

        // create order
        Order order = new Order();
        order.setCustomer(customer);
        order.setCart(cart);

        // create payment
        Payment payment = new Payment();
        payment.setTotalAmount(cart.getTotalAmount());

        // create shop-payments
        List<Shop> shops = cartShopService.getCartShops(cartId);

        for (Shop shop : shops) {

            ShopPayment shopPayment = new ShopPayment();
            Long amount = 0L;
            List<Product> products = cartShopService.getCartProducts(cartId, shop.getId());
            for (Product product : products) {
                amount += product.getPrice();
            }

            shopPayment.setAmount(amount);
            shopPayment.setShop(shop);
            shopPayment.setPayment(payment);

            // save shop-payment in db
            ShopPayment savedShopPayment = shopService.store(shopPayment);

            payment.addShopPayment(savedShopPayment);
        }
        // save payment in db
        Payment savedPayment = paymentService.store(payment);

        order.setPayment(savedPayment);

        // save order in db
        return orderRepository.save(order);
    }
}