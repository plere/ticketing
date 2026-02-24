package com.example.reservationservice.tempreservation.adapter.in.web;

import com.example.checkauth.UserToken;
import com.example.reservationservice.tempreservation.model.User;
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
