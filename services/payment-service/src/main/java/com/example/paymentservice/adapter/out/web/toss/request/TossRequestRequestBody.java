package com.example.paymentservice.adapter.out.web.toss.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class TossRequestRequestBody {
    private String paymentKey;
    private String orderId;
    private String amount;
}
