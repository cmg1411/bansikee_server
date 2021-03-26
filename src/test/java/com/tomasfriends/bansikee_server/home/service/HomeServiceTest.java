package com.tomasfriends.bansikee_server.home.service;

import com.tomasfriends.bansikee_server.home.service.dto.HomeResponseDto;
import com.tomasfriends.bansikee_server.mypage.domain.repository.MyPlantRepository;
import com.tomasfriends.bansikee_server.onBoarding.domain.Plant;
import com.tomasfriends.bansikee_server.onBoarding.repository.PlantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.assertj.core.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
class HomeServiceTest {

    @Autowired private HomeService homeService;
    @Autowired private PlantRepository plantRepository;
    @Autowired private MyPlantRepository myPlantRepository;
    @Autowired private WebApplicationContext context;
    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }


//    @Test
//    @DisplayName("홈 화면 가져오기")
//    @WithMockUser(roles = "USER")
//    void getHomeTest() {
//        HomeResponseDto home = homeService.getHome();
//
//        assertThat(home.getGreeting()).isEqualTo(Greeting.getDayGreet());
//    }

    @Test
    @DisplayName("랜덤 식물 거져오기 테스트")
    void randomTest() {
        Plant randomPlant = homeService.getRandomPlant();

        System.out.println(randomPlant.toString());

        assertThat(randomPlant).isNotNull();
    }
}