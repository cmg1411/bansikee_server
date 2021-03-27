package com.tomasfriends.bansikee_server.home.service;

import com.tomasfriends.bansikee_server.common.AuthenticationUser;
import com.tomasfriends.bansikee_server.home.service.dto.HomeResponseDto;
import com.tomasfriends.bansikee_server.mypage.domain.repository.MyPlantRepository;
import com.tomasfriends.bansikee_server.onBoarding.domain.Plant;
import com.tomasfriends.bansikee_server.onBoarding.repository.PlantRepository;
import com.tomasfriends.bansikee_server.sign.service.CustomUserDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class HomeServiceTest {

    @Autowired private HomeService homeService;

    @Test
    @DisplayName("홈 화면 가져오기")
    @Transactional
    @WithUserDetails(value = "1", userDetailsServiceBeanName = "customUserDetailService")
    void getHomeTest() {
        HomeResponseDto home = homeService.getHome();

        assertThat(home.getRecommendPlantId()).isBetween(1,3000);
        assertThat(home).isNotNull();
    }

    @Test
    @DisplayName("랜덤 식물 거져오기 테스트")
    void randomTest() {
        Plant randomPlant = homeService.getRandomPlant();

        System.out.println(randomPlant.toString());

        assertThat(randomPlant).isNotNull();
    }
}