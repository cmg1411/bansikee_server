package com.tomasfriends.bansikee_server.mypage.service;

import com.tomasfriends.bansikee_server.mypage.domain.Diary;
import com.tomasfriends.bansikee_server.mypage.domain.PlantRegistration;
import com.tomasfriends.bansikee_server.mypage.domain.repository.DiaryRepository;
import com.tomasfriends.bansikee_server.mypage.domain.repository.MyPlantRepository;
import com.tomasfriends.bansikee_server.mypage.domain.repository.PictureRepository;
import com.tomasfriends.bansikee_server.mypage.service.dto.PictureUrls;
import com.tomasfriends.bansikee_server.mypage.service.dto.Watered;
import com.tomasfriends.bansikee_server.mypage.service.dto.Weather;
import com.tomasfriends.bansikee_server.mypage.service.dto.req.DiaryRequestDto;
import com.tomasfriends.bansikee_server.onBoarding.domain.Plant;
import com.tomasfriends.bansikee_server.onBoarding.repository.PlantRepository;
import com.tomasfriends.bansikee_server.sign.domain.repository.UserRepository;
import com.tomasfriends.bansikee_server.sign.service.CustomUserDetailService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class DiaryServiceTest {

//    @MockBean DiaryRepository diaryRepository;
//    @MockBean PictureRepository pictureRepository;
//    @MockBean PlantRepository plantRepository;
//    @MockBean UserRepository userRepository;
//    @MockBean MyPlantRepository myPlantRepository;
//    @InjectMocks DiaryService diaryService;

    @Autowired DiaryService diaryService;
    @Autowired MyPlantRepository myPlantRepository;
    @Autowired PlantRepository plantRepository;

    @DisplayName("일기 삽입 테스트")
    @Test
    @WithUserDetails(value = "1", userDetailsServiceBeanName = "customUserDetailService")
    void pictureSaveTest() {

    }

//    @DisplayName("일기 삽입 테스트")
//    @Test
//    void diaryWriteTest() {
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