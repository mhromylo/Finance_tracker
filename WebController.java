package com.example.financeTracker.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/register")
        public String showRegisterPage() {
            return "register";
    }
}
