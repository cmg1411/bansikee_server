package com.tomasfriends.bansikee_server.controller.logincontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomasfriends.bansikee_server.domain.login.accesstoken.AccessObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class KakaoLoginControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @Test
    @DisplayName("@Valid 에러 테스트")
    void isValidObject() throws Exception {
        // given
        AccessObject accessObject = AccessObject.builder()
            .accessToken("")
            .build();

        // when
        String kakaoAccessTokenJsonString = objectMapper.writeValueAsString(accessObject);

        // then
        mockMvc.perform(post("/sginup/kakao")
            .contentType(MediaType.APPLICATION_JSON)
            .content(kakaoAccessTokenJsonString))
            .andExpect(status().is(403));
    }
}