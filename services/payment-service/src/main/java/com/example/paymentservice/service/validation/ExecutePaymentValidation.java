package com.example.paymentservice.service.validation;

import com.example.paymentservice.domain.ExecutePaymentCommand;
import com.example.paymentservice.port.out.GetPaymentEventPort;
import com.example.paymentservice.service.exception.ExecutePaymentValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExecutePaymentValidation {
    private final GetPaymentEventPort getPaymentEventPort;

    public void isValid(ExecutePaymentCommand command) {
        isExistReadyPayment(command);
        checkAmount(command);
    }

    private void isExistReadyPayment(ExecutePaymentCommand command) {
        getPaymentEventPort.findByOrderId(command.orderId())
            .orElseThrow(ExecutePaymentValidationException::new);
    }

    private void checkAmount(ExecutePaymentCommand command) {
        if (!getPaymentEventPort.getByOrderId(command.orderId()).amount().equals(command.amount())) {
            throw new ExecutePaymentValidationException();
        }
    }
}
