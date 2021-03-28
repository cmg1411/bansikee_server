package com.tomasfriends.bansikee_server.mypage.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tomasfriends.bansikee_server.mypage.service.PlantRegisterService;
import com.tomasfriends.bansikee_server.mypage.service.dto.Watered;
import com.tomasfriends.bansikee_server.mypage.service.dto.Weather;
import com.tomasfriends.bansikee_server.mypage.service.dto.req.DiaryRequestDto;
import com.tomasfriends.bansikee_server.mypage.service.dto.req.PlantRegistrationRequestDto;
import com.tomasfriends.bansikee_server.mypage.service.dto.resp.DiaryListResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class PlantRegistrationControllerTest {

    @MockBean private PlantRegisterService plantRegisterService;
    @MockBean private List<DiaryListResponseDto> list;

    @Autowired MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @WithUserDetails(value = "1", userDetailsServiceBeanName = "customUserDetailService")
    @DisplayName("식물을 내 식물로 등록한다.")
    @Test
    void registerMyPlantTest() throws Exception {
        PlantRegistrationRequestDto plantRegistrationRequestDto = PlantRegistrationRequestDto.builder()
            .plantBirth(LocalDateTime.now())
            .plantId(1)
            .plantIntro("hi")
            .plantNickName("name")
            .waterPeriod(3)
            .pictureUrl("asdf")
            .build();

        // @RequestBody 로 받아오는 객체에서 원하는 형태로 변환되지 않기 때문에 HttpMessageNotReadableException 가 발생
        // @RequestBody 로 받아오는 객체에서도 읽을 수 있게 형식을 지정해주는 것이다
        // https://dundung.tistory.com/270
        byte[] content = objectMapper
            .registerModule(new JavaTimeModule())
            .writeValueAsBytes(plantRegistrationRequestDto);

        mockMvc.perform(post("/myplant/registration")
            .accept(MediaType.APPLICATION_JSON) // 응답 미디어 타입 지정
            .contentType(MediaType.APPLICATION_JSON_VALUE) // 컨텐츠 미디어 타입 지정 (이거 안하면 415)
            .content(content))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @WithUserDetails(value = "1", userDetailsServiceBeanName = "customUserDetailService")
    @DisplayName("내 식물 리스트를 조회한다.")
    @Test
    void getMyPlantListTest() throws Exception {

        mockMvc.perform(get("/myplant"))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @WithUserDetails(value = "1", userDetailsServiceBeanName = "customUserDetailService")
    @DisplayName("내 식물 상세 조회한다.")
    @Test
    void getMyPlantTest() throws Exception {

        mockMvc.perform(get("/myplant/{myPlantId}", 1)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @WithUserDetails(value = "1", userDetailsServiceBeanName = "customUserDetailService")
    @DisplayName("일지를 작성한다.")
    @Test
    void writeDiaryTest() throws Exception {
        PlantRegistrationRequestDto plantRegistrationRequestDto = PlantRegistrationRequestDto.builder()
            .plantBirth(LocalDateTime.now())
            .plantId(1)
            .plantIntro("hi")
            .plantNickName("name")
            .waterPeriod(3)
            .pictureUrl("asdf")
            .build();

        byte[] content = objectMapper
            .registerModule(new JavaTimeModule())
            .writeValueAsBytes(plantRegistrationRequestDto);

        mockMvc.perform(patch("/myplant/modify")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(content))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @WithUserDetails(value = "1", userDetailsServiceBeanName = "customUserDetailService")
    @DisplayName("내 식물을 목록에서 삭제한다.")
    @Test
    void deleteMyPlantTest() throws Exception {

        mockMvc.perform(delete("/myplant/delete/{myPlantId}", 1)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(print());
    }
}