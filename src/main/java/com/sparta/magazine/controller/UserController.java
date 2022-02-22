package com.sparta.magazine.controller;

import com.sparta.magazine.dto.LoginRequestDto;
import com.sparta.magazine.dto.LoginResponseDto;
import com.sparta.magazine.dto.UserRequestDto;
import com.sparta.magazine.dto.UserResponseDto;
import com.sparta.magazine.jwt.JwtFilter;
import com.sparta.magazine.jwt.TokenProvider;
import com.sparta.magazine.model.User;
import com.sparta.magazine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {
    private final UserService userService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    @Autowired
    public UserController(UserService userService, AuthenticationManagerBuilder authenticationManagerBuilder, TokenProvider tokenProvider) {
        this.userService = userService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/api/register")
    public UserResponseDto register(@Valid @RequestBody UserRequestDto requestDto) {
        return userService.register(requestDto);
    }

    @PostMapping("/api/login")
    public ResponseEntity<LoginResponseDto> UserLogin(@Valid @RequestBody LoginRequestDto requestDto){
        User user = userService.loginUser(requestDto);
        String username = user.getUsername();
        String nickName = user.getNickName();

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new LoginResponseDto(true,"로그인 성공",jwt,username,nickName), httpHeaders, HttpStatus.OK);
    }
}