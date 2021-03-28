package com.tomasfriends.bansikee_server.jwt;

import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JwtProviderTest {

    @Autowired JwtProvider jwtProvider;
    private BansikeeUser user;
    private String jwt = null;

    @BeforeEach
    void setAuthUser() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwidXNlcklkeCI6MiwiZW1haWwiOiIyMWppbW15QGhhbm1haWwubmV0Iiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImV4cCI6MTYyNTA1MTUzMn0.rGpG9H2OU6b_QI4Vzj5gQzI2gLe0QI6svweYeZqVb2o";

        Authentication authentication = jwtProvider.getAuthentication(token);
        user = (BansikeeUser) authentication.getPrincipal();
    }

    @Order(1)
    @DisplayName("토큰으로 인증유저를 얻을 수 있다.")
    @Test
    void getAuthenticationTest() {
        assertThat(user.getEmail()).isEqualTo("21jimmy@hanmail.net");
    }

    @Order(2)
    @DisplayName("유저정보로 Jwt 토큰을 받는다.")
    @Test
    void getJwtTest() {
        // when
        jwt = jwtProvider.getJWT(user, Arrays.asList("USER"));

        // then
        assertThat(jwt).isNotNull();
    }

    @Order(3)
    @DisplayName("토큰인증이 성공하면 true 를 반환한다.")
    @Test
    void decodeJwtSuccessTest() {
        // when
        boolean b = jwtProvider.validateToken(jwt);

        // then
        assertThat(b).isTrue();
    }

    @Order(2)
    @DisplayName("토큰인증이 실패하면 false 를 반환한다.")
    @Test
    void decodeJwtFailTest() {
        // when
        boolean b = jwtProvider.validateToken("invalidToken");

        // then
        assertThat(b).isFalse();
    }
}