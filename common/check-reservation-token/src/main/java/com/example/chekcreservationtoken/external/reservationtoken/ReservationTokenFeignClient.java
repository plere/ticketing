package com.example.chekcreservationtoken.external.reservationtoken;


import com.example.httpresponse.response.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
    name = "reservationTokenFeignClient",
    url = "${external.client.reservation-token.url}"
)
public interface ReservationTokenFeignClient {
    @GetMapping("/internal/reservation-token/concerts/validation")
    ResponseDto<Boolean> isValid(@SpringQueryMap ReservationToken reservationToken);
}
