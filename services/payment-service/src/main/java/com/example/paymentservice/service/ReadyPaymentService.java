package com.example.paymentservice.service;

import com.example.paymentservice.domain.PaymentEvent;
import com.example.paymentservice.domain.ReadyPaymentEventCommand;
import com.example.paymentservice.port.in.ReadyPaymentUseCase;
import com.example.paymentservice.port.out.SavePaymentEventPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadyPaymentService implements ReadyPaymentUseCase {
    private final SavePaymentEventPort savePaymentEventPort;

    @Override
    public PaymentEvent ready(ReadyPaymentEventCommand command) {
        return savePaymentEventPort.save(command.toPaymentEvent());
    }
}
