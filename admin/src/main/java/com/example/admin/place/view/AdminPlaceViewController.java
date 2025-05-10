package com.example.admin.place.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/admin/places")
public class AdminPlaceViewController {
//    @GetMapping("/seat")
//    public String seat() {
//        return "admin_seat_register";
//    }

    @GetMapping
    public String getCreatePlaceView() {
        return "admin_place_register";
    }
}
