package com.example.paymentservice.port.out;

import com.example.paymentservice.domain.ExecutePaymentCommand;
import com.example.paymentservice.domain.PaymentExecutionResult;

public interface PaymentExecutorPort {
    PaymentExecutionResult execute(ExecutePaymentCommand command);
}
