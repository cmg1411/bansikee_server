package com.tomasfriends.bansikee_server.mypage.domain;

import com.tomasfriends.bansikee_server.mypage.domain.exceptions.IllegalAuthUserException;
import com.tomasfriends.bansikee_server.mypage.service.DiaryService;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyPlantTest {
    @Autowired DiaryService myPlant;

    @DisplayName("인증자의 아이디가 매개변수로 전달된 아이디와 같은지 테스트")
    @Test
    @WithUserDetails(value = "1", userDetailsServiceBeanName = "customUserDetailService")
    void checkAuthTest() {
        BansikeeUser user = myPlant.checkAuth(1);

        Assertions.assertThat(user.getId()).isEqualTo(1);
    }
    @DisplayName("인증자의 아이디가 매개변수로 전달된 아이디와 다르면 예외 발생")
    @Test
    @WithUserDetails(value = "1", userDetailsServiceBeanName = "customUserDetailService")
    void checkInvalidAuthTest() {
        assertThrows(IllegalAuthUserException.class, () -> myPlant.checkAuth(2));
    }
}