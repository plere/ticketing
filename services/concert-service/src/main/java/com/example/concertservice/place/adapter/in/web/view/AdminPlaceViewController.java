package com.example.concertservice.place.adapter.in.web.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/admin/places")
public class AdminPlaceViewController {
    @GetMapping
    public String getCreatePlaceView() {
        return "admin_place_register";
    }
}
