package com.sparta.magazine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/api/register")
    public String registerForm() {
        return "registerForm";
    }

    @GetMapping("/api/login")
    public String loginForm() {
        return "loginForm";
    }
}
