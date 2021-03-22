package com.tomasfriends.bansikee_server.mypage.service;

import com.tomasfriends.bansikee_server.mypage.domain.Diary;
import com.tomasfriends.bansikee_server.mypage.domain.DiaryPicture;
import com.tomasfriends.bansikee_server.mypage.domain.PlantRegistration;
import com.tomasfriends.bansikee_server.mypage.domain.repository.DiaryRepository;
import com.tomasfriends.bansikee_server.mypage.domain.repository.MyPlantRepository;
import com.tomasfriends.bansikee_server.mypage.domain.repository.PictureRepository;
import com.tomasfriends.bansikee_server.mypage.service.dto.Watered;
import com.tomasfriends.bansikee_server.mypage.service.dto.Weather;
import com.tomasfriends.bansikee_server.onBoarding.domain.Plant;
import com.tomasfriends.bansikee_server.onBoarding.repository.PlantRepository;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import com.tomasfriends.bansikee_server.sign.domain.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DiaryServiceTest {

    @Autowired DiaryRepository diaryRepository;
    @Autowired PictureRepository pictureRepository;
    @Autowired PlantRepository plantRepository;
    @Autowired UserRepository userRepository;
    @Autowired MyPlantRepository myPlantRepository;


    @DisplayName("일기 삽입 테스트")
    @Test
    void pictureSaveTest() {
        BansikeeUser user = userRepository.findById(1).get();
        Plant plant = plantRepository.findById(1).get();
        PlantRegistration plantRegistration = new PlantRegistration("abc", plant, "nic", LocalDateTime.now(), "gkgkgk", 3, user);

        myPlantRepository.save(plantRegistration);

        Diary diary = new Diary(Weather.BAD, 10, Watered.YES, "zz", plantRegistration, user);
        diaryRepository.save(diary);

        DiaryPicture picture = new DiaryPicture(diary, "pictureUrl1");
        pictureRepository.save(picture);
    }

//    @DisplayName("일기 삽입 테스트")
//    @Test
//    void diaryWriteTest() {
//
//
//        Diary diary = Diary.builder()
//            .myPlant(new PlantRegistration("abc", new Plant(), "ttt", LocalDateTime.now(), "안녕하세요", 3, new BansikeeUser()))
//            .pictures(urls)
//            .contents("오늘은 많이 안자랐다.")
//            .height(11)
//            .build();
//
//        diaryRepository.save();
//    }
}