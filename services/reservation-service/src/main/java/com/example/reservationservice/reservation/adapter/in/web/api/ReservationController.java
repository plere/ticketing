package com.example.reservationservice.reservation.adapter.in.web.api;

import com.example.checkauth.UserToken;
import com.example.httpresponse.response.CreatedResponseDto;
import com.example.httpresponse.response.ResponseDto;
import com.example.reservationservice.reservation.adapter.in.web.request.ExecutePaymentRequest;
import com.example.reservationservice.reservation.adapter.in.web.response.CheckoutPaymentResponse;
import com.example.reservationservice.reservation.application.port.in.ReservationExecutePaymentUseCase;
import com.example.reservationservice.reservation.application.port.in.ReservationPayCheckoutUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.reservationservice.reservation.adapter.in.web.response.ReservationResponseCode.CHECKOUT_PAYMENT_SUCCESS;
import static com.example.reservationservice.reservation.adapter.in.web.response.ReservationResponseCode.PAY_AND_RESERVATION_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationPayCheckoutUseCase reservationPayCheckoutUseCase;
    private final ReservationExecutePaymentUseCase reservationExecutePaymentUseCase;

    @GetMapping("/payment/{id}")
    public ResponseEntity<ResponseDto<CheckoutPaymentResponse>> checkout(@PathVariable Long id, UserToken userToken) {
        return ResponseDto.from(
            CHECKOUT_PAYMENT_SUCCESS,
            CheckoutPaymentResponse.from(
                reservationPayCheckoutUseCase.checkout(id, userToken.getId())
            )
        );
    }

    @PostMapping("/payment")
    public ResponseEntity<ResponseDto<CreatedResponseDto>> executePayment(@RequestBody ExecutePaymentRequest request, UserToken userToken) {
        return CreatedResponseDto.from(
            reservationExecutePaymentUseCase.execute(request.toCommand(userToken)).id(),
            PAY_AND_RESERVATION_SUCCESS
        );
    }
}
