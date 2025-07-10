package com.example.concertservice.place.adapter.in.web.api;

import com.example.concertservice.place.adapter.in.web.request.CreatePlaceRequest;
import com.example.concertservice.place.adapter.in.web.response.AdminPlaceResponseCode;
import com.example.concertservice.place.adapter.in.web.response.GetAllPlacesResponse;
import com.example.concertservice.place.adapter.in.web.response.GetAllSeatsByPlaceIdResponse;
import com.example.concertservice.place.application.port.in.usecase.PlaceCreateUseCase;
import com.example.concertservice.place.application.port.in.usecase.PlaceGetAllUseCase;
import com.example.concertservice.place.application.port.in.usecase.PlaceSeatGetAllByPlaceIdUseCase;
import com.example.httpresponse.response.CreatedResponseDto;
import com.example.httpresponse.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/places")
public class AdminPlaceController {
    private final PlaceCreateUseCase placeCreateUseCase;
    private final PlaceGetAllUseCase placeGetAllUseCase;
    private final PlaceSeatGetAllByPlaceIdUseCase placeSeatGetAllByPlaceIdUseCase;

    @GetMapping
    public ResponseDto<GetAllPlacesResponse> getAllPlaces() {
        return ResponseDto.from(
            AdminPlaceResponseCode.GET_ALL_PLACES,
            GetAllPlacesResponse.from(placeGetAllUseCase.getAllPlaces())
        );
    }

    @GetMapping("/{placeId}/seats")
    public ResponseDto<GetAllSeatsByPlaceIdResponse> getAllSeatsByPlaceId(@PathVariable Long placeId) {
        return ResponseDto.from(
            AdminPlaceResponseCode.GET_ALL_SEATS_BY_PLACE_ID,
            GetAllSeatsByPlaceIdResponse.from(placeSeatGetAllByPlaceIdUseCase.getAllByPlaceId(placeId))
        );
    }

    @PostMapping
    public ResponseDto<CreatedResponseDto> createPlace(@RequestBody CreatePlaceRequest request) {
        return CreatedResponseDto.from(placeCreateUseCase.create(request.toModel()), AdminPlaceResponseCode.CREATE_PLACE);
    }
}
