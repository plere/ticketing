package com.example.paymentservice.port.out;

import com.example.paymentservice.domain.PaymentEvent;

public interface SavePaymentEventPort {
    PaymentEvent save(PaymentEvent paymentEvent);
}
