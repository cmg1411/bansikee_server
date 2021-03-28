package com.tomasfriends.bansikee_server.mypage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomasfriends.bansikee_server.mypage.service.DiaryService;
import com.tomasfriends.bansikee_server.mypage.service.dto.Watered;
import com.tomasfriends.bansikee_server.mypage.service.dto.Weather;
import com.tomasfriends.bansikee_server.mypage.service.dto.req.DiaryRequestDto;
import com.tomasfriends.bansikee_server.mypage.service.dto.resp.DiaryListResponseDto;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class DiaryControllerTest {

    @MockBean private DiaryService diaryService;
    @MockBean private List<DiaryListResponseDto> list;
    @Autowired MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @WithUserDetails(value = "1", userDetailsServiceBeanName = "customUserDetailService")
    @DisplayName("일지를 작성한다.")
    @Test
    void writeDiaryTest() throws Exception {
        DiaryRequestDto diaryRequestDto = DiaryRequestDto.builder()
            .weather(Weather.BAD)
            .watered(Watered.NO)
            .height(33)
            .contents("kk")
            .myPlantId(1)
            .build();

        mockMvc.perform(post("/registration/diary")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsBytes(diaryRequestDto)))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @WithUserDetails(value = "1", userDetailsServiceBeanName = "customUserDetailService")
    @DisplayName("내 식물의 다이어리 리스트를 가져온다.")
    @Test
    void getDiaryListTest() throws Exception {

        mockMvc.perform(get("/diary/{myPlantId}", 1)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @WithUserDetails(value = "1", userDetailsServiceBeanName = "customUserDetailService")
    @DisplayName("내 식물의 상세 다이어리를 가져온다.")
    @Test
    void getDiaryTest() throws Exception {

        mockMvc.perform(get("/diary/plantDiary/{diaryId}", 1)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @WithUserDetails(value = "1", userDetailsServiceBeanName = "customUserDetailService")
    @DisplayName("내 식물의 다이어리를 삭제한다.")
    @Test
    void deleteDiaryTest() throws Exception {

        mockMvc.perform(delete("/diary/delete/{diaryId}", 1)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(print());
    }
}