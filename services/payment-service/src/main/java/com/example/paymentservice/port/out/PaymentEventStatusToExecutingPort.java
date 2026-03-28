package com.example.paymentservice.port.out;

public interface PaymentEventStatusToExecutingPort {
    void toExecutingStatus(String orderId);
}
