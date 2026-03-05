package com.example.reservationservice.reservation.adapter.out.web.pay.feign;

import com.example.httpresponse.response.CreatedResponseDto;
import com.example.httpresponse.response.ResponseDto;
import com.example.reservationservice.reservation.adapter.out.web.pay.feign.dto.ReadyPaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
    name = "payFeignClient",
    url = "${external.client.payment.url}"
)
public interface PayFeignClient {
    @PostMapping("/internal/payment/ready")
    ResponseDto<CreatedResponseDto> readyPayment(ReadyPaymentRequest request);
}
