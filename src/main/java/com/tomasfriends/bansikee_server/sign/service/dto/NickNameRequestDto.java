package com.tomasfriends.bansikee_server.sign.service.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class NickNameRequestDto {

    @NotBlank
    private String nickName;
}
