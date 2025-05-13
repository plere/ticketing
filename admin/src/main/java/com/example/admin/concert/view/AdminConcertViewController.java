package com.example.admin.concert.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/admin/concerts")
public class AdminConcertViewController {
    @GetMapping
    public String index() {
        return "concert/concert_register";
    }


}
