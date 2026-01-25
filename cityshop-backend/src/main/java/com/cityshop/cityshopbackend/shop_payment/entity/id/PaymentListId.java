package com.cityshop.cityshopbackend.shop_payment.entity.id;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PaymentListId implements Serializable {

    private Long paymentId;
    private Long shopPaymentId;
}

