package com.cityshop.cityshopbackend.order.entity;

import com.cityshop.cityshopbackend.order.entity.id.OrderShopProductId;
import com.cityshop.cityshopbackend.product.Product;
import com.cityshop.cityshopbackend.shop.Shop;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "order_shop_product")
public class OrderShopProduct {

    @EmbeddedId
    private OrderShopProductId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("shopId")
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
