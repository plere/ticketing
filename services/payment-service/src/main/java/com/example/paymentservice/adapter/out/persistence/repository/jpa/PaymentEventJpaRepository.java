package com.example.paymentservice.adapter.out.persistence.repository.jpa;

import com.example.paymentservice.adapter.out.persistence.entity.PaymentEventEntity;
import com.example.paymentservice.domain.PaymentStatus;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentEventJpaRepository extends JpaRepository<PaymentEventEntity, Long> {
    Optional<PaymentEventEntity> findByOrderId(String orderId);

    Optional<PaymentEventEntity> findByOrderIdAndStatusIs(String orderId, PaymentStatus status);

    @Modifying
    @Query("update PaymentEventEntity p set p.status = 'EXECUTING' where p.orderId=:orderId and p.status = :status")
    int updateExecutingStatus(@Param("orderId") String orderId, @Param("status") PaymentStatus status);
}
