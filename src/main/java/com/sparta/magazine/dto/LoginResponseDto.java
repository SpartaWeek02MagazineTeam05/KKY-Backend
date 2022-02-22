package com.sparta.magazine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    private boolean result;
    private String msg;
    private String token;
    private String username;
    private String nickName;
}
