package com.example.paymentservice.adapter.in.web.api.internal;

import com.example.httpresponse.response.CreatedResponseDto;
import com.example.httpresponse.response.ResponseDto;
import com.example.paymentservice.adapter.in.web.request.ExecutePaymentRequest;
import com.example.paymentservice.adapter.in.web.request.ReadyPaymentRequest;
import com.example.paymentservice.port.in.ExecutePaymentUseCase;
import com.example.paymentservice.port.in.ReadyPaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.paymentservice.adapter.in.web.response.PaymentResponseCode.READY_PAYMENT_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/payment")
public class InternalPaymentController {
    private final ReadyPaymentUseCase readyPaymentUseCase;
    private final ExecutePaymentUseCase executePaymentUseCase;

    @PostMapping
    public ResponseEntity<ResponseDto<CreatedResponseDto>> execute(@RequestBody ExecutePaymentRequest request) {
        return CreatedResponseDto.from(executePaymentUseCase.execute(request.toCommand()).id(), READY_PAYMENT_SUCCESS);
    }

    @PostMapping("/ready")
    public ResponseEntity<ResponseDto<CreatedResponseDto>> ready(@RequestBody ReadyPaymentRequest request) {
        return CreatedResponseDto.from(readyPaymentUseCase.ready(request.toCommand()).id(), READY_PAYMENT_SUCCESS);
    }
}
