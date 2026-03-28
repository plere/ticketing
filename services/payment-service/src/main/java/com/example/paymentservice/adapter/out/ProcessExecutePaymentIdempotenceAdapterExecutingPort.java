package com.example.paymentservice.adapter.out;

import com.example.paymentservice.adapter.out.persistence.repository.jpa.PaymentEventJpaRepository;
import com.example.paymentservice.domain.PaymentStatus;
import com.example.paymentservice.port.out.PaymentEventStatusToExecutingPort;
import com.example.paymentservice.service.exception.PaymentEventStatusConsistencyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ProcessExecutePaymentIdempotenceAdapterExecutingPort implements PaymentEventStatusToExecutingPort {
    private final PaymentEventJpaRepository paymentEventJpaRepository;

    @Override
    @Transactional
    public void toExecutingStatus(String orderId) {
        int resultRows = paymentEventJpaRepository.updateExecutingStatus(orderId, PaymentStatus.NOT_STARTED);
        if (resultRows != 1) {
            throw new PaymentEventStatusConsistencyException();
        }
    }
}
