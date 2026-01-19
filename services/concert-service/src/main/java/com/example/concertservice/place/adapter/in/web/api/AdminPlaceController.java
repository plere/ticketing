package com.example.concertservice.place.adapter.in.web.api;

import com.example.concertservice.place.adapter.in.web.request.CreatePlaceRequest;
import com.example.concertservice.place.adapter.in.web.response.AdminPlaceResponseCode;
import com.example.concertservice.place.adapter.in.web.response.GetAllPlacesResponse;
import com.example.concertservice.place.adapter.in.web.response.GetAllSeatsByPlaceIdResponse;
import com.example.concertservice.place.application.port.in.usecase.PlaceCreateUseCase;
import com.example.concertservice.place.application.port.in.usecase.PlaceGetUseCase;
import com.example.concertservice.place.application.port.in.usecase.PlaceSeatGetAllByPlaceIdUseCase;
import com.example.httpresponse.response.CreatedResponseDto;
import com.example.httpresponse.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/places")
public class AdminPlaceController {
    private final PlaceCreateUseCase placeCreateUseCase;
    private final PlaceGetUseCase placeGetUseCase;
    private final PlaceSeatGetAllByPlaceIdUseCase placeSeatGetAllByPlaceIdUseCase;

    @GetMapping
    public ResponseEntity<ResponseDto<GetAllPlacesResponse>> getAllPlaces() {
        return ResponseDto.from(
            AdminPlaceResponseCode.GET_ALL_PLACES,
            GetAllPlacesResponse.from(placeGetUseCase.getAllPlaces())
        );
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ResponseDto<GetAllPlacesResponse>> getAllPlaces(@PathVariable String name) {
        return ResponseDto.from(
            AdminPlaceResponseCode.GET_ALL_PLACES,
            GetAllPlacesResponse.from(placeGetUseCase.getAllPlacesByName(name))
        );
    }

    @GetMapping("/{placeId}/seats")
    public ResponseEntity<ResponseDto<GetAllSeatsByPlaceIdResponse>> getAllSeatsByPlaceId(@PathVariable Long placeId) {
        return ResponseDto.from(
            AdminPlaceResponseCode.GET_ALL_SEATS_BY_PLACE_ID,
            GetAllSeatsByPlaceIdResponse.from(placeSeatGetAllByPlaceIdUseCase.getAllByPlaceId(placeId))
        );
    }

    @PostMapping
    public ResponseEntity<ResponseDto<CreatedResponseDto>> createPlace(@RequestBody CreatePlaceRequest request) {
        return CreatedResponseDto.from(placeCreateUseCase.create(request.toModel()), AdminPlaceResponseCode.CREATE_PLACE);
    }
}
