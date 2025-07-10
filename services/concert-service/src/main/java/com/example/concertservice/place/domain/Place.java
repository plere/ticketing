package com.example.concertservice.place.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Place {
    private Long id;
    private String name;
    private String address;
    private int totalSeatsCount;
    private List<PlaceSeat> seats;
}
