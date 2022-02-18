package com.sparta.magazine.controller;

import com.sparta.magazine.dto.LoginRequestDto;
import com.sparta.magazine.dto.UserRequestDto;
import com.sparta.magazine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.regex.Pattern;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/api/register")
    @ResponseBody
    public boolean register(@RequestBody UserRequestDto requestDto) {

        boolean validateNickName = Pattern.matches("^[a-zA-Z0-9]{3,10}$", requestDto.getNickName());
        boolean validatePwd = Pattern.matches("^[a-zA-Z0-9]{4,13}$", requestDto.getUserPwd());

        if (validatePwd == false) {
            return false;
        } else if (validateNickName == false || requestDto.getNickName().equals(requestDto.getUserPwd())) {
            return false;
        } else if (!requestDto.getUserPwd().equals(requestDto.getUserPwdCheck())) {
            return false;
        } else {
            userService.register(requestDto);
            return true;
        }
    }

    @GetMapping("/api/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/api/login")
    @ResponseBody
    public boolean login(@RequestBody LoginRequestDto requestDto) {
        return userService.login(requestDto);
    }
}
