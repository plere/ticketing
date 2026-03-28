package com.example.paymentservice.service.validation;

import com.example.paymentservice.domain.ExecutePaymentCommand;
import com.example.paymentservice.domain.PaymentStatus;
import com.example.paymentservice.port.out.GetPaymentEventPort;
import com.example.paymentservice.service.exception.DuplicateExecutePaymentException;
import com.example.paymentservice.service.exception.ExecutePaymentValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class ExecutePaymentValidation {
    private final GetPaymentEventPort getPaymentEventPort;
    private final RedisTemplate<String, Object> redisTemplate;

    public void isValid(ExecutePaymentCommand command) {
        checkDuplicate(command);
        isExistReadyPayment(command);
        checkAmount(command);
    }

    private void checkDuplicate(ExecutePaymentCommand command) {
        Boolean isNotDuplicated = redisTemplate.opsForValue().setIfAbsent(command.orderId(), "", Duration.ofSeconds(5));
        if (!isNotDuplicated) {
            throw new DuplicateExecutePaymentException();
        }
    }

    private void isExistReadyPayment(ExecutePaymentCommand command) {
        getPaymentEventPort.findByOrderIdAndStatus(command.orderId(), PaymentStatus.NOT_STARTED)
            .orElseThrow(ExecutePaymentValidationException::new);
    }

    private void checkAmount(ExecutePaymentCommand command) {
        if (!getPaymentEventPort.getByOrderId(command.orderId()).amount().equals(command.amount())) {
            throw new ExecutePaymentValidationException();
        }
    }
}
