package com.example.paymentservice.port.out;

import com.example.paymentservice.domain.PaymentEvent;

import java.util.Optional;

public interface GetPaymentEventPort {
    Optional<PaymentEvent> findByOrderId(String orderId);

    PaymentEvent getByOrderId(String orderId);
}
