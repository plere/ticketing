package com.example.paymentservice.port.out;

import com.example.paymentservice.domain.PaymentEvent;
import com.example.paymentservice.domain.PaymentStatus;

import java.util.Optional;

public interface GetPaymentEventPort {
    Optional<PaymentEvent> findByOrderIdAndStatus(String orderId, PaymentStatus status);

    PaymentEvent getByOrderId(String orderId);
}
