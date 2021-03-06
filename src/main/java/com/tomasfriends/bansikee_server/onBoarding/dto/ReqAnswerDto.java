package com.tomasfriends.bansikee_server.onBoarding.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqAnswerDto {

    @NotBlank
    private int questionIdx;

    @NotBlank
    private int optionIdx;
}
