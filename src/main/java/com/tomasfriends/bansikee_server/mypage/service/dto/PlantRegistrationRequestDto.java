package com.tomasfriends.bansikee_server.mypage.service.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
public class PlantRegistrationRequestDto {

    private String pictureUrl;

    @Positive
    private Integer plantId;

    @NotBlank
    private String plantNickName;
    private LocalDateTime plantBirth;
    private String plantIntro;

    @Positive
    private int waterPeriod;
}
