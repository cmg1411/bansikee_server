package com.tomasfriends.bansikee_server.mypage.service.dto.req;

import com.tomasfriends.bansikee_server.mypage.domain.Diary;
import com.tomasfriends.bansikee_server.mypage.domain.DiaryPicture;
import com.tomasfriends.bansikee_server.mypage.domain.PlantRegistration;
import com.tomasfriends.bansikee_server.mypage.service.dto.PictureUrls;
import com.tomasfriends.bansikee_server.mypage.service.dto.Watered;
import com.tomasfriends.bansikee_server.mypage.service.dto.Weather;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DiaryRequestDto {

    private Integer myPlantId;
    private PictureUrls dailyPictures;
    private Weather weather;
    private Integer height;
    private Watered watered;
    private String contents;

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
        return dailyPictures.getUrls().stream()
            .map(p -> new DiaryPicture(diary, p))
            .collect(Collectors.toList());
    }
}
