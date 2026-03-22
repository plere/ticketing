package com.example.paymentservice.port.in;

import com.example.paymentservice.domain.ExecutePaymentCommand;
import com.example.paymentservice.domain.PaymentEvent;

public interface ExecutePaymentUseCase {
    PaymentEvent execute(ExecutePaymentCommand command);
}
