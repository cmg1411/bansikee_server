package com.tomasfriends.bansikee_server.user.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqUserDto {

    @NotBlank
    private String name;

    @NotBlank
    private String userImageUrl;
}
