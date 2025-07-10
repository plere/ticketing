package com.example.concertservice.place.adapter.in.web.request;

import com.example.concertservice.place.domain.Place;
import com.example.concertservice.place.domain.PlaceSeat;

import java.util.List;

public record CreatePlaceRequest(
    String name,
    String address,
    List<SeatRequest> seats
) {

    public record SeatRequest(
        int floor,
        List<SeatRowRequest> rows
    ) {
        public record SeatRowRequest(
            int rowOrder,
            String name,
            int count
        ) {
        }

        public List<PlaceSeat> toModel() {
            return rows.stream()
                .map(r -> PlaceSeat.builder()
                    .floor(floor)
                    .rowOrder(r.rowOrder)
                    .rowName(r.name)
                    .rowCount(r.count)
                    .build())
                .toList();
        }

        private int getFloorSeatCount() {
            return rows.stream().mapToInt(SeatRowRequest::count).sum();
        }
    }

    public Place toModel() {
        return Place.builder()
            .name(name)
            .address(address)
            .totalSeatsCount(getTotalSeatsCount())
            .seats(seats.stream().flatMap(seat -> seat.toModel().stream()).toList())
            .build();
    }

    private int getTotalSeatsCount() {
        return seats.stream().mapToInt(SeatRequest::getFloorSeatCount).sum();
    }
}
