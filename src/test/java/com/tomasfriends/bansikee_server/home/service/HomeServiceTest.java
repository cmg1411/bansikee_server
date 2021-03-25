package com.tomasfriends.bansikee_server.home.service;

import com.tomasfriends.bansikee_server.onBoarding.domain.Plant;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HomeServiceTest {

    @Autowired private HomeService homeService;

    @Test
    @DisplayName("랜덤 식물 거져오기 테스트")
    void randomTest() {
        Plant randomPlant = homeService.getRandomPlant();

        System.out.println(randomPlant.toString());

        Assertions.assertThat(randomPlant).isNotNull();
    }
}