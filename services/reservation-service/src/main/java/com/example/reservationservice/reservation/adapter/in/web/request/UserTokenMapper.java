package com.example.reservationservice.reservation.adapter.in.web.request;

import com.example.checkauth.UserToken;
import com.example.reservationservice.reservation.domain.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserTokenMapper {
    public User toUser(UserToken token) {
        return User.builder()
            .id(token.getId())
            .email(token.getEmail())
            .build();
    }
}
