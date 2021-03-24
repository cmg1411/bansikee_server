package com.tomasfriends.bansikee_server.mypage.service.dto.req;

import com.tomasfriends.bansikee_server.mypage.domain.PlantRegistration;
import com.tomasfriends.bansikee_server.onBoarding.domain.Plant;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
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

    public PlantRegistration toEntity(BansikeeUser user, Plant plant) {
        return PlantRegistration.builder()
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
