package com.tomasfriends.bansikee_server.sign.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequestDto {

    @NotBlank
    private String accessToken;
}
