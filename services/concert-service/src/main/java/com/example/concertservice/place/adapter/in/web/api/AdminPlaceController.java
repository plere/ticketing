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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Place/Admin")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/places")
public class AdminPlaceController {
    private final PlaceCreateUseCase placeCreateUseCase;
    private final PlaceGetUseCase placeGetUseCase;
    private final PlaceSeatGetAllByPlaceIdUseCase placeSeatGetAllByPlaceIdUseCase;

    @GetMapping
    @Operation(summary = "모든 장소 조회")
    public ResponseEntity<ResponseDto<GetAllPlacesResponse>> getAllPlaces() {
        return ResponseDto.from(
            AdminPlaceResponseCode.GET_ALL_PLACES,
            GetAllPlacesResponse.from(placeGetUseCase.getAllPlaces())
        );
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "장소명 매칭되는 모든 장소 조회")
    public ResponseEntity<ResponseDto<GetAllPlacesResponse>> getAllPlaces(@PathVariable String name) {
        return ResponseDto.from(
            AdminPlaceResponseCode.GET_ALL_PLACES,
            GetAllPlacesResponse.from(placeGetUseCase.getAllPlacesByName(name))
        );
    }

    @GetMapping("/{placeId}/seats")
    @Operation(summary = "특정 장소의 모든 좌석 조회")
    public ResponseEntity<ResponseDto<GetAllSeatsByPlaceIdResponse>> getAllSeatsByPlaceId(@PathVariable Long placeId) {
        return ResponseDto.from(
            AdminPlaceResponseCode.GET_ALL_SEATS_BY_PLACE_ID,
            GetAllSeatsByPlaceIdResponse.from(placeSeatGetAllByPlaceIdUseCase.getAllByPlaceId(placeId))
        );
    }

    @PostMapping
    @Operation(summary = "장소 생성")
    public ResponseEntity<ResponseDto<CreatedResponseDto>> createPlace(@RequestBody CreatePlaceRequest request) {
        return CreatedResponseDto.from(placeCreateUseCase.create(request.toModel()), AdminPlaceResponseCode.CREATE_PLACE);
    }
}
