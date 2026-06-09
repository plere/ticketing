package com.example.paymentservice.adapter.in.web.api.internal;

import com.example.httpresponse.response.CreatedResponseDto;
import com.example.httpresponse.response.ResponseDto;
import com.example.paymentservice.adapter.in.web.request.ExecutePaymentRequest;
import com.example.paymentservice.adapter.in.web.request.ReadyPaymentRequest;
import com.example.paymentservice.port.in.ExecutePaymentUseCase;
import com.example.paymentservice.port.in.ReadyPaymentUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.paymentservice.adapter.in.web.response.PaymentResponseCode.READY_PAYMENT_SUCCESS;

@Tag(name = "Payment/internal")
@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/payment")
public class InternalPaymentController {
    private final ReadyPaymentUseCase readyPaymentUseCase;
    private final ExecutePaymentUseCase executePaymentUseCase;

    @PostMapping
    @Operation(summary = "결제 진행 API")
    public ResponseEntity<ResponseDto<CreatedResponseDto<Void>>> execute(@RequestBody ExecutePaymentRequest request) {
        return CreatedResponseDto.from(executePaymentUseCase.execute(request.toCommand()).id(), READY_PAYMENT_SUCCESS);
    }

    @PostMapping("/ready")
    @Operation(summary = "결제 전 준비 API")
    public ResponseEntity<ResponseDto<CreatedResponseDto<Void>>> ready(@RequestBody ReadyPaymentRequest request) {
        return CreatedResponseDto.from(readyPaymentUseCase.ready(request.toCommand()).id(), READY_PAYMENT_SUCCESS);
    }
}
