package com.tomasfriends.bansikee_server.mypage.service.dto;

import com.tomasfriends.bansikee_server.mypage.domain.PlantRegistration;
import com.tomasfriends.bansikee_server.onBoarding.domain.Plant;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
public class MyPlantPatchRequestDto {

    private Integer myPlantId;
    private String pictureUrl;
    private LocalDateTime plantBirth;
    private String plantIntro;

    @Positive
    private Integer plantId;

    @NotBlank
    private String plantNickName;

    @Positive
    private int waterPeriod;

    @Builder
    public PlantRegistration toPatchEntity(BansikeeUser user, Plant plant) {
        return PlantRegistration.builder()
            .id(myPlantId)
            .pictureUrl(pictureUrl)
            .user(user)
            .plant(plant)
            .plantNickName(plantNickName)
            .plantBirth(plantBirth)
            .plantIntroduce(plantIntro)
            .wateringCycle(waterPeriod)
            .build();
    }
}
