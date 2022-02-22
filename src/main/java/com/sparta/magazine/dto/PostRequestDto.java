package com.sparta.magazine.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDto {
    private String content;
    private String nickName;
    private String image;
    private String type;
}
