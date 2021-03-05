package com.tomasfriends.bansikee_server.controller.logincontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomasfriends.bansikee_server.dto.controllerdto.AccessToken;
import com.tomasfriends.bansikee_server.dto.controllerdto.BasicLoginUserRequest;
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
        AccessToken accessObject = new AccessToken("");

        // when
        String kakaoAccessTokenJsonString = objectMapper.writeValueAsString(accessObject);

        // then
        mockMvc.perform(post("/sginup/kakao")
            .contentType(MediaType.APPLICATION_JSON)
            .content(kakaoAccessTokenJsonString))
            .andExpect(status().is(404));
    }

//    @Test
//    @DisplayName("일반 회원가입 @Valid 테스트")
//    void isValidUserSignUp() throws Exception {
//        // given
//        String name = "";
//        String email = "11";
//        String password = "123";
//        String passwordRe = "123";
//
//        // when
//        BasicLoginUserRequest user = BasicLoginUserRequest.builder()
//                                .email(email)
//                                .name(name)
//                                .password(password)
//                                .passwordRe(passwordRe)
//                                    .build();
//
//        // then
//        mockMvc.perform(post("/v1/signup")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(String.valueOf(user)))
//            .andExpect(status().is(404));
//    }
}