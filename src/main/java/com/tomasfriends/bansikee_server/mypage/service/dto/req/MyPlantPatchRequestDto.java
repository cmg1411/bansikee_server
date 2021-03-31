package com.tomasfriends.bansikee_server.mypage.service.dto.req;

import com.tomasfriends.bansikee_server.mypage.domain.PlantRegistration;
import com.tomasfriends.bansikee_server.onBoarding.domain.Plant;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MyPlantPatchRequestDto {

    private final Integer myPlantId;
    private final String pictureUrl;
    private final LocalDateTime plantBirth;
    private final String plantIntro;

    @Positive
    private final Integer plantId;

    @NotBlank
    private final String plantNickName;

    @Positive
    private final int waterPeriod;

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
