package com.tomasfriends.bansikee_server.sign.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class SignInRequestDto {

    @NotBlank
    private final String email;
    @NotBlank
    private final String password;
}
