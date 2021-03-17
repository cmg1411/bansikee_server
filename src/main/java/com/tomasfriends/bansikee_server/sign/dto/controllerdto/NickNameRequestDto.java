package com.tomasfriends.bansikee_server.sign.dto.controllerdto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class NickNameRequestDto {

    @NotBlank
    private String nickName;
}
