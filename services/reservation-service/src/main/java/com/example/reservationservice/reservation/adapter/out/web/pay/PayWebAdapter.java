package com.example.reservationservice.reservation.adapter.out.web.pay;

import com.example.reservationservice.reservation.adapter.out.web.pay.feign.PayFeignClient;
import com.example.reservationservice.reservation.adapter.out.web.pay.feign.dto.ReadyPaymentRequest;
import com.example.reservationservice.reservation.application.port.out.PaymentExecutorPort;
import com.example.reservationservice.reservation.domain.ReadyPaymentResult;
import com.example.reservationservice.reservation.domain.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PayWebAdapter implements PaymentExecutorPort {
    private final PayFeignClient payFeignClient;

    @Override
    public ReadyPaymentResult ready(Reservation reservation) {
        try {
            payFeignClient.readyPayment(ReadyPaymentRequest.of(reservation));
        } catch (Exception e) {
            return ReadyPaymentResult.createFailResult();
        }

        return ReadyPaymentResult.createSuccessResult();
    }
}
