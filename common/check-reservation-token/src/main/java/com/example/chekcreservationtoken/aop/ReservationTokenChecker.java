package com.example.chekcreservationtoken.aop;

import com.example.chekcreservationtoken.exception.InvalidReservationTokenException;
import com.example.chekcreservationtoken.external.reservationtoken.ReservationToken;
import com.example.chekcreservationtoken.external.reservationtoken.ReservationTokenFeignClient;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class ReservationTokenChecker {
    private final ReservationTokenFeignClient reservationTokenFeignClient;

    @Before("@annotation(requiredReservationToken)")
    public void check(JoinPoint joinPoint, RequiredReservationToken requiredReservationToken) {
        boolean isValid = false;
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof ReservationToken token) {
                isValid = reservationTokenFeignClient.isValid(token).getBody();
            }
        }

        if (!isValid) {
            throw new InvalidReservationTokenException();
        }
    }
}

