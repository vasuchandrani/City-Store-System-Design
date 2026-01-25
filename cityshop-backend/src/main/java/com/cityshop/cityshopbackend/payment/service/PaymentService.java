package com.cityshop.cityshopbackend.payment.service;

import com.cityshop.cityshopbackend.payment.Payment;
import com.cityshop.cityshopbackend.payment.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment store(Payment payment) {
        return paymentRepository.save(payment);
    }
}
