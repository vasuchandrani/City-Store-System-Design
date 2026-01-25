package com.cityshop.cityshopbackend.shop_payment.entity;

import com.cityshop.cityshopbackend.payment.Payment;
import com.cityshop.cityshopbackend.shop_payment.entity.id.PaymentListId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "list_payment")
@Getter
@Setter
public class PaymentList {

    @EmbeddedId
    private PaymentListId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("paymentId")
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("shopPaymentId")
    @JoinColumn(name = "shop_payment_id", nullable = false)
    private ShopPayment shopPayment;
}
