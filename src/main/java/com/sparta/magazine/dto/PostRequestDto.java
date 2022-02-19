package com.sparta.magazine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDto {
    private String content;
    private Long like;
    private String username;
    private String image;
}
