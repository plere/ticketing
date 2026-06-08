package com.example.chekcreservationtoken.aop;

import com.example.chekcreservationtoken.exception.InvalidReservationTokenException;
import com.example.chekcreservationtoken.external.reservationtoken.ReservationToken;
import com.example.chekcreservationtoken.external.reservationtoken.ReservationTokenFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ReservationTokenChecker {
    private final ReservationTokenFeignClient reservationTokenFeignClient;
    private final ObjectMapper objectMapper;

    @Before("@annotation(requiredReservationToken)")
    public void check(JoinPoint joinPoint, RequiredReservationToken requiredReservationToken) {
        boolean isValid = false;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        try {
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();

                String headerValue = request.getHeader("reservation-token");

                if (headerValue != null && !headerValue.isEmpty()) {
                    String decodeValue = URLDecoder.decode(headerValue);
                    Map<String, String> dataMap = new HashMap<>();
                    String[] pairs = decodeValue.split("&");

                    for (String pair : pairs) {
                        String[] keyValue = pair.split("=");
                        if (keyValue.length == 2) {
                            dataMap.put(keyValue[0], keyValue[1]);
                        }
                    }

                    ReservationToken token = objectMapper.convertValue(dataMap, ReservationToken.class);
                    isValid = reservationTokenFeignClient.isValid(token).getBody();
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        if (!isValid) {
            throw new InvalidReservationTokenException();
        }
    }
}

