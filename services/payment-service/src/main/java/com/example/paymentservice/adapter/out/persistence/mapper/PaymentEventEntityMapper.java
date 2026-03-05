package com.example.paymentservice.adapter.out.persistence.mapper;

import com.example.paymentservice.adapter.out.persistence.entity.PaymentEventEntity;
import com.example.paymentservice.domain.PaymentEvent;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PaymentEventEntityMapper {
    public PaymentEventEntity mapToEntity(PaymentEvent paymentEvent) {
        return PaymentEventEntity.builder()
            .id(paymentEvent.id())
            .userId(paymentEvent.userId())
            .orderId(paymentEvent.orderId())
            .orderName(paymentEvent.orderName())
            .paymentKey(paymentEvent.paymentKey())
            .approvedAt(paymentEvent.approvedAt())
            .isPaymentDone(paymentEvent.isPaymentDone())
            .build();
    }

    public PaymentEvent mapToDomain(PaymentEventEntity entity) {
        return PaymentEvent.builder()
            .id(entity.getId())
            .userId(entity.getUserId())
            .orderId(entity.getOrderId())
            .orderName(entity.getOrderName())
            .paymentKey(entity.getPaymentKey())
            .approvedAt(entity.getApprovedAt())
            .isPaymentDone(entity.getIsPaymentDone())
            .build();
    }
}
