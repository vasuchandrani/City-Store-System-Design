package com.cityshop.cityshopbackend.cart.entity.id;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CartShopId implements Serializable {

    private Long shopId;
    private Long productId;
    private Long cartId;
}
