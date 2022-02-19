package com.sparta.magazine.controller;

import com.sparta.magazine.dto.UserRequestDto;
import com.sparta.magazine.dto.UserResponseDto;
import com.sparta.magazine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/user/register")
    @ResponseBody
    public UserResponseDto register(UserRequestDto requestDto) {
        return userService.register(requestDto);
    }

    @GetMapping("/user/login")
    public String loginForm() {
        return "login";
    }

}
