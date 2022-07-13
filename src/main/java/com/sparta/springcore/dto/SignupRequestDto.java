package com.sparta.springcore.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String nickname;
    private String password;
    private String password_check;
    private boolean admin = false;
    private String adminToken = "";
}