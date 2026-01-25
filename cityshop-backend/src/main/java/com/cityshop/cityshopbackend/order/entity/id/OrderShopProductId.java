package com.cityshop.cityshopbackend.order.entity.id;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderShopProductId implements Serializable {

    private Long orderId;
    private Long shopId;
    private Long productId;
}
