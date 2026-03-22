package com.example.reservationservice.reservation.adapter.out.web.pay.feign;

import com.example.reservationservice.reservation.adapter.out.web.pay.feign.dto.ExecutePaymentFeignRequest;
import com.example.reservationservice.reservation.adapter.out.web.pay.feign.dto.ReadyPaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
    name = "payFeignClient",
    url = "${external.client.payment.url}"
)
public interface PayFeignClient {
    @PostMapping("/internal/payment/ready")
    void readyPayment(ReadyPaymentRequest request);

    @PostMapping("/internal/payment")
    void execute(ExecutePaymentFeignRequest request);
}
