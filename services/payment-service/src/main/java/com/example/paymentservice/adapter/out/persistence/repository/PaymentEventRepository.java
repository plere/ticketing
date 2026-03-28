package com.example.paymentservice.adapter.out.persistence.repository;

import com.example.paymentservice.adapter.out.persistence.mapper.PaymentEventEntityMapper;
import com.example.paymentservice.adapter.out.persistence.repository.jpa.PaymentEventJpaRepository;
import com.example.paymentservice.domain.PaymentEvent;
import com.example.paymentservice.domain.PaymentStatus;
import com.example.paymentservice.port.out.GetPaymentEventPort;
import com.example.paymentservice.port.out.SavePaymentEventPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentEventRepository implements SavePaymentEventPort, GetPaymentEventPort {
    private final PaymentEventJpaRepository paymentEventJpaRepository;

    @Override
    @Transactional
    public PaymentEvent save(PaymentEvent paymentEvent) {
        return PaymentEventEntityMapper.mapToDomain(
            paymentEventJpaRepository.save(PaymentEventEntityMapper.mapToEntity(paymentEvent))
        );
    }

    @Override
    public Optional<PaymentEvent> findByOrderIdAndStatus(String orderId, PaymentStatus status) {
        return paymentEventJpaRepository.findByOrderIdAndStatusIs(orderId, status)
            .map(PaymentEventEntityMapper::mapToDomain);
    }

    @Override
    public PaymentEvent getByOrderId(String orderId) {
        return paymentEventJpaRepository.findByOrderId(orderId)
            .map(PaymentEventEntityMapper::mapToDomain)
            .orElseThrow();
    }
}
