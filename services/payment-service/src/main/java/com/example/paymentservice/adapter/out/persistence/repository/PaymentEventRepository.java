package com.example.paymentservice.adapter.out.persistence.repository;

import com.example.paymentservice.adapter.out.persistence.mapper.PaymentEventEntityMapper;
import com.example.paymentservice.adapter.out.persistence.repository.jpa.PaymentEventJpaRepository;
import com.example.paymentservice.domain.PaymentEvent;
import com.example.paymentservice.port.out.SavePaymentEventPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PaymentEventRepository implements SavePaymentEventPort {
    private final PaymentEventJpaRepository paymentEventJpaRepository;

    @Override
    @Transactional
    public PaymentEvent save(PaymentEvent paymentEvent) {
        return PaymentEventEntityMapper.mapToDomain(
            paymentEventJpaRepository.save(PaymentEventEntityMapper.mapToEntity(paymentEvent))
        );
    }
}
