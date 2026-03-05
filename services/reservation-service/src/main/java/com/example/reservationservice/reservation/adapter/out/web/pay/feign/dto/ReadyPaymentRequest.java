package com.example.reservationservice.reservation.adapter.out.web.pay.feign.dto;

import com.example.reservationservice.reservation.domain.Reservation;
import lombok.Builder;

@Builder
public record ReadyPaymentRequest(
    Long userId,
    String orderName,
    String orderId
) {
    public static ReadyPaymentRequest of(Reservation reservation) {
        return ReadyPaymentRequest.builder()
            .userId(reservation.userId())
            .orderId(reservation.orderId())
            .orderName(reservation.concertName())
            .build();
    }
}
