package com.sparta.springcore.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    // Post 수정관련 정보 - 정보를 물고 다닌다.
    // domain - Post (데이터베이스영역)으로 접근을 하지 않도록 하기 위함
    private String title;
    private String name;
    private String password;
    private String post;
}
