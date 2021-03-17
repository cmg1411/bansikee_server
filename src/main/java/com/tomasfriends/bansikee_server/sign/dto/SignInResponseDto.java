package com.tomasfriends.bansikee_server.sign.dto;

import lombok.Getter;

@Getter
public class SignInResponseDto {

    private final String jwt;
    private final String name;
    private final String email;

    public SignInResponseDto(String jwt, String name, String email) {
        this.jwt = jwt;
        this.name = name;
        this.email = email;
    }
}
