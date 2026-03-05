package com.example.paymentservice.port.in;

import com.example.paymentservice.domain.PaymentEvent;
import com.example.paymentservice.domain.ReadyPaymentEventCommand;

public interface ReadyPaymentUseCase {
    PaymentEvent ready(ReadyPaymentEventCommand paymentEvent);
}
