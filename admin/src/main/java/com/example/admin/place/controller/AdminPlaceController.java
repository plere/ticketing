package com.example.admin.place.controller;

import com.example.admin.place.controller.dto.AdminPlaceResponseCode;
import com.example.admin.place.controller.dto.CreatePlaceRequest;
import com.example.admin.place.controller.dto.GetAllPlacesResponse;
import com.example.admin.place.controller.dto.GetAllSeatsByPlaceIdResponse;
import com.example.admin.place.service.AdminPlaceSeatService;
import com.example.admin.place.service.AdminPlaceService;
import com.example.httpresponse.response.CreatedResponseDto;
import com.example.httpresponse.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/places")
public class AdminPlaceController {
    private final AdminPlaceService placeService;
    private final AdminPlaceSeatService placeSeatService;

    @GetMapping
    public ResponseDto<GetAllPlacesResponse> getAllPlaces() {
        return ResponseDto.from(AdminPlaceResponseCode.GET_ALL_PLACES, placeService.getAllPlaces());
    }

    @GetMapping("/{placeId}/seats")
    public ResponseDto<GetAllSeatsByPlaceIdResponse> getAllSeatsByPlaceId(@PathVariable Long placeId) {
        return ResponseDto.from(AdminPlaceResponseCode.GET_ALL_SEATS_BY_PLACE_ID, GetAllSeatsByPlaceIdResponse.from(placeSeatService.getAllByPlaceId(placeId)));
    }

    @PostMapping
    public ResponseDto<CreatedResponseDto> createPlace(@RequestBody CreatePlaceRequest request) {
        return CreatedResponseDto.from(placeService.createPlace(request), AdminPlaceResponseCode.CREATE_PLACE);
    }
}
