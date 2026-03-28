package com.example.paymentservice.service;

import com.example.paymentservice.domain.ExecutePaymentCommand;
import com.example.paymentservice.domain.PaymentEvent;
import com.example.paymentservice.domain.PaymentExecutionResult;
import com.example.paymentservice.port.in.ExecutePaymentUseCase;
import com.example.paymentservice.port.out.GetPaymentEventPort;
import com.example.paymentservice.port.out.PaymentEventStatusToExecutingPort;
import com.example.paymentservice.port.out.PaymentExecutorPort;
import com.example.paymentservice.port.out.SavePaymentEventPort;
import com.example.paymentservice.service.validation.ExecutePaymentValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExecutePaymentService implements ExecutePaymentUseCase {
    private final ExecutePaymentValidation executePaymentValidation;
    private final PaymentExecutorPort paymentExecutorPort;
    private final GetPaymentEventPort getPaymentEventPort;
    private final SavePaymentEventPort savePaymentEventPort;
    private final PaymentEventStatusToExecutingPort paymentEventStatusToExecutingPort;

    @Override
    public PaymentEvent execute(ExecutePaymentCommand command) {
        executePaymentValidation.isValid(command);
        paymentEventStatusToExecutingPort.toExecutingStatus(command.orderId());
        PaymentExecutionResult result = paymentExecutorPort.execute(command);
        return savePaymentEventPort.save(convertToPaymentEvent(result));
    }

    private PaymentEvent convertToPaymentEvent(PaymentExecutionResult result) {
        PaymentEvent event = getPaymentEventPort.getByOrderId(result.orderId());
        return PaymentEvent.builder()
            .id(event.id())
            .userId(event.userId())
            .orderId(event.orderId())
            .orderName(event.orderName())
            .paymentKey(event.paymentKey())
            .amount(event.amount())
            .approvedAt(result.extraDetails().approvedAt())
            .status(result.paymentStatus())
            .isPaymentDone(true)
            .rawData(result.toString())
            .build();
    }
}
