package com.example.admin.place.controller.dto;

import com.example.admin.place.model.PlaceSeat;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;

@Builder
public record GetAllSeatsByPlaceIdResponse(
    List<FloorSeatResponse> seats
) {
    @Builder
    public record FloorSeatResponse(
        int floor,
        List<SeatResponse> floorSeats
    ) {
        @Builder
        public record SeatResponse(
            long id,
            int rowOrder,
            String rowName,
            int rowCount
        ) {
            public static SeatResponse from(PlaceSeat placeSeat) {
                return SeatResponse.builder()
                    .id(placeSeat.getId())
                    .rowOrder(placeSeat.getRowOrder())
                    .rowName(placeSeat.getRowName())
                    .rowCount(placeSeat.getRowCount())
                    .build();
            }
        }
    }

    public static GetAllSeatsByPlaceIdResponse from(List<PlaceSeat> seats) {
        List<FloorSeatResponse> response = new ArrayList<>();
        seats.stream().collect(groupingBy(PlaceSeat::getFloor)).forEach((k, v) -> {
            response.add(
                FloorSeatResponse.builder()
                    .floor(k)
                    .floorSeats(new ArrayList<>(v.stream().map(FloorSeatResponse.SeatResponse::from).toList()))
                    .build()
            );
        });

        return GetAllSeatsByPlaceIdResponse.builder()
            .seats(response)
            .build();
    }
}
