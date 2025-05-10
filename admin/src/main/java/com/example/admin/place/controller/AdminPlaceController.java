package com.example.admin.place.controller;

import com.example.admin.place.controller.dto.CreatePlaceRequest;
import com.example.admin.place.service.AdminPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/places")
public class AdminPlaceController {
    private final AdminPlaceService adminPlaceService;

    @PostMapping
    public void createPlace(@RequestBody CreatePlaceRequest request) {
        adminPlaceService.createPlace(request);
    }
}
