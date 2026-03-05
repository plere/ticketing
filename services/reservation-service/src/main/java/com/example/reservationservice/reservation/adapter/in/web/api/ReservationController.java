package com.example.reservationservice.reservation.adapter.in.web.api;

import com.example.checkauth.UserToken;
import com.example.httpresponse.response.ResponseDto;
import com.example.reservationservice.reservation.adapter.in.web.response.CheckoutPaymentResponse;
import com.example.reservationservice.reservation.application.port.in.ReservationPayCheckoutUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.reservationservice.reservation.adapter.in.web.response.ReservationResponseCode.CHECKOUT_PAYMENT_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationPayCheckoutUseCase reservationPayCheckoutUseCase;

    @GetMapping("/payment/{id}")
    public ResponseEntity<ResponseDto<CheckoutPaymentResponse>> checkout(@PathVariable Long id, UserToken userToken) {
        return ResponseDto.from(
            CHECKOUT_PAYMENT_SUCCESS,
            CheckoutPaymentResponse.from(
                reservationPayCheckoutUseCase.checkout(id, userToken.getId())
            )
        );
    }
}
