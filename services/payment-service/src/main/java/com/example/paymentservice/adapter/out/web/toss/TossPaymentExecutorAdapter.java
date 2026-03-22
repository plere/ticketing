package com.example.paymentservice.adapter.out.web.toss;

import com.example.paymentservice.adapter.out.web.toss.exception.PSPConfirmationException;
import com.example.paymentservice.adapter.out.web.toss.exception.TossPaymentError;
import com.example.paymentservice.adapter.out.web.toss.request.TossRequestRequestBody;
import com.example.paymentservice.adapter.out.web.toss.response.TossFailureResponse;
import com.example.paymentservice.adapter.out.web.toss.response.TossPaymentConfirmationResponse;
import com.example.paymentservice.domain.*;
import com.example.paymentservice.port.out.PaymentExecutorPort;
import io.netty.handler.timeout.TimeoutException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class TossPaymentExecutorAdapter implements PaymentExecutorPort {
    private final WebClient tossPaymentWebClient;
    private static final String uri = "/v1/payments/confirm";

    @Override
    public PaymentExecutionResult execute(ExecutePaymentCommand command) {
        return tossPaymentWebClient.post()
            .uri(uri)
            .header("Idempotency-Key", command.orderId())
            .bodyValue(
                TossRequestRequestBody.builder()
                    .paymentKey(command.paymentKey())
                    .orderId(command.orderId())
                    .amount(command.amount().toString())
                    .build()
            )
            .retrieve()
            .onStatus(
                statusCode -> statusCode.is4xxClientError() || statusCode.is5xxServerError(),
                response -> response.bodyToMono(TossFailureResponse.class)
                    .flatMap(tossFailure -> {
                        TossPaymentError error = TossPaymentError.get(tossFailure.code());
                        return Mono.error(new PSPConfirmationException(
                            error.getDescription(),
                            null,
                            error.name(),
                            error.isSuccess(),
                            error.isFailure(),
                            error.isUnknown(),
                            error.isRetryableError()
                        ));
                    })
            )
            .bodyToMono(TossPaymentConfirmationResponse.class)
            .map(it ->
                new PaymentExecutionResult(
                    command.paymentKey(),
                    command.orderId(),
                    new PaymentExtraDetails(
                        PaymentType.valueOf(it.type()),
                        PaymentMethod.get(it.method()),
                        LocalDateTime.parse(it.approvedAt(), DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                        it.orderName(),
                        PSPConfirmationStatus.valueOf(it.status()),
                        (long) it.totalAmount(),
                        it.toString()
                    ),
                    null,
                    true,
                    false,
                    false,
                    false
                )
            )
            .retryWhen(Retry.backoff(2, Duration.ofSeconds(1)).jitter(0.1)
                .filter(
                    it ->
                        (it instanceof PSPConfirmationException && ((PSPConfirmationException) it).isRetryableError())
                            || it instanceof TimeoutException)
                .onRetryExhaustedThrow(
                    (retryBackoffSpec, retrySignal) -> (PSPConfirmationException) retrySignal.failure())
            ).block();
    }
}
