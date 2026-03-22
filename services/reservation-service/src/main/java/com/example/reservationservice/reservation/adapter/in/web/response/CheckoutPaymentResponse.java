package com.example.reservationservice.reservation.adapter.in.web.response;

import com.example.reservationservice.reservation.domain.Reservation;
import lombok.Builder;

@Builder
public record CheckoutPaymentResponse(
    String orderId,
    String orderName,
    String amount
) {
    public static CheckoutPaymentResponse from(Reservation reservation) {
        return CheckoutPaymentResponse.builder()
            .orderId(reservation.orderId())
            .orderName(reservation.concertName())
            .amount(reservation.amount())
            .build();
    }
}
