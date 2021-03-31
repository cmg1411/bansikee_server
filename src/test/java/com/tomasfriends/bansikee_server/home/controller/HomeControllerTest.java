package com.tomasfriends.bansikee_server.home.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomasfriends.bansikee_server.home.service.HomeService;
import com.tomasfriends.bansikee_server.home.service.dto.HomeResponseDto;
import com.tomasfriends.bansikee_server.mypage.domain.PlantRegistration;
import com.tomasfriends.bansikee_server.onBoarding.domain.Plant;
import com.tomasfriends.bansikee_server.response.service.ResponseService;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {

    @MockBean
    private HomeService homeService;

    @MockBean
    private ResponseService responseService;

    @Mock
    BansikeeUser user;
    @Mock
    List<PlantRegistration> twoPlant;
    @Autowired MockMvc mockMvc;
    private Plant plant = null;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        plant = new Plant(1, "이름", "종", "정보", "쉬움", 1, 1, "빠름", "냄새", "온도", "물", "물", "빛", "범위", "유알엘|", 1, 1, 1, 1);
        objectMapper = new ObjectMapper();
    }

    @WithUserDetails(value = "1", userDetailsServiceBeanName = "customUserDetailService")
    @DisplayName("홈 화면을 조회한다.")
    @Test
    void getHomePage() throws Exception {
        HomeResponseDto homeResponses = new HomeResponseDto();

        homeResponses.setUser(user);
        homeResponses.setGreeting("안녕하세요");
        homeResponses.setRandomRecommendPlant(plant);
        homeResponses.setTwoMyPlants(twoPlant);

        given(homeService.getHome()).willReturn(homeResponses);

        mockMvc.perform(post("/home")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(print());
    }
}