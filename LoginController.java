package com.example.financeTracker.demo.controller;

import com.example.financeTracker.demo.model.User;
import com.example.financeTracker.demo.service.PasswordEncoderService;
import com.example.financeTracker.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private PasswordEncoderService passwordEncoder;
    private UserService userService;

    @GetMapping("/login")
    public String showloginPage() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard";
    }


    @GetMapping("/max")
    public String showMaxPage() {
        return "max";
    }
}
