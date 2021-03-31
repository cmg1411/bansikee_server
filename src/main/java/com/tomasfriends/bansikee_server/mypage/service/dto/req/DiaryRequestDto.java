package com.tomasfriends.bansikee_server.mypage.service.dto.req;

import com.tomasfriends.bansikee_server.mypage.domain.Diary;
import com.tomasfriends.bansikee_server.mypage.domain.DiaryPicture;
import com.tomasfriends.bansikee_server.mypage.domain.PlantRegistration;
import com.tomasfriends.bansikee_server.mypage.service.dto.Watered;
import com.tomasfriends.bansikee_server.mypage.service.dto.Weather;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class DiaryRequestDto {

    private final Integer myPlantId;
    private final List<String> dailyPictures;
    private final Weather weather;
    private final Integer height;
    private final Watered watered;
    private final String contents;

    public Diary toDiaryEntity(PlantRegistration plant, BansikeeUser user) {
        return Diary.builder()
            .weather(weather)
            .height(height)
            .contents(contents)
            .myPlant(plant)
            .water(watered)
            .user(user)
            .build();
    }

    public List<DiaryPicture> toDiaryPictureEntities(Diary diary) {
        return dailyPictures.stream()
            .map(p -> new DiaryPicture(diary, p))
            .collect(Collectors.toList());
    }
}
