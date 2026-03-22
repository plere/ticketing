package com.example.paymentservice.adapter.out.persistence.repository.jpa;

import com.example.paymentservice.adapter.out.persistence.entity.PaymentEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentEventJpaRepository extends JpaRepository<PaymentEventEntity, Long> {
    Optional<PaymentEventEntity> findByOrderId(String orderId);
}
