package com.tomasfriends.bansikee_server.mypage.domain;

import com.tomasfriends.bansikee_server.mypage.domain.repository.DiaryRepository;
import com.tomasfriends.bansikee_server.mypage.service.DiaryService;
import com.tomasfriends.bansikee_server.mypage.service.dto.Watered;
import com.tomasfriends.bansikee_server.mypage.service.dto.Weather;
import com.tomasfriends.bansikee_server.mypage.service.dto.resp.DiaryListResponseDto;
import com.tomasfriends.bansikee_server.mypage.service.dto.resp.DiaryResponseDto;
import com.tomasfriends.bansikee_server.onBoarding.domain.Plant;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class DiaryTest {

    @MockBean private BansikeeUser bansikeeUser;
    @MockBean private Plant plant;
    private Diary diary;

    @BeforeEach
    void setUp() {
        PlantRegistration plantRegistration = PlantRegistration.builder()
            .id(1)
            .plant(plant)
            .user(bansikeeUser)
            .pictureUrl("asdf")
            .plantIntroduce("asdf")
            .plantNickName("asdf")
            .wateringCycle(3)
            .plantBirth(LocalDateTime.now())
            .build();

        diary = Diary.builder()
            .water(Watered.YES)
            .user(bansikeeUser)
            .myPlant(plantRegistration)
            .contents("1")
            .height(1)
            .weather(Weather.BAD)
            .build();
    }

    @Test
    void diaryTest() {
        assertThat(diary).isNotNull();
    }

    @Test
    void toEntityTest() {
        assertThat(diary.toDiaryResponseDto()).isInstanceOf(DiaryResponseDto.class);
    }

    @Test
    void toListResponseDtoTest() {
        DiaryListResponseDto diaryListResponseDto = diary.toListResponseDto(1, new DiaryPicture(), LocalDateTime.now().toLocalDate());

        assertThat(diaryListResponseDto).isInstanceOf(DiaryListResponseDto.class)
        .isNotNull();
    }
}