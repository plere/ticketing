package com.example.reservationservice.reservation.adapter.out.web.pay.feign.dto;

import com.example.reservationservice.reservation.domain.Reservation;
import lombok.Builder;

@Builder
public record ExecutePaymentFeignRequest(
    String orderId,
    String paymentKey,
    String amount
) {
    public static ExecutePaymentFeignRequest of(Reservation reservation) {
        return ExecutePaymentFeignRequest.builder()
            .orderId(reservation.orderId())
            .paymentKey(reservation.paymentKey())
            .amount(reservation.amount())
            .build();
    }
}
