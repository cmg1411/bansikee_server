package com.tomasfriends.bansikee_server.sign.service;

import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import com.tomasfriends.bansikee_server.sign.domain.repository.UserRepository;
import com.tomasfriends.bansikee_server.sign.service.dto.*;
import com.tomasfriends.bansikee_server.sign.service.exceptions.AlreadySignedUpException;
import com.tomasfriends.bansikee_server.sign.service.exceptions.HaveToSignInWithOauthException;
import com.tomasfriends.bansikee_server.sign.service.exceptions.InvalidPasswordException;
import com.tomasfriends.bansikee_server.sign.service.exceptions.NotRegisteredEmailException;
import com.tomasfriends.bansikee_server.sign.service.oauth2.KakaoOAuthService;
import com.tomasfriends.bansikee_server.sign.service.oauth2.OAuthTokenToTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BansikeeUserServiceTest extends OAuthTokenToTest {

    @Autowired private BansikeeUserService bansikeeUserService;
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private KakaoOAuthService kakaoOAuthService;

    @Transactional
    @DisplayName("일반 회원가입 테스트")
    @Test
    void signUpTest() {
        BasicLoginUserRequest dto = BasicLoginUserRequest.builder()
            .email("test@test.com")
            .name("test")
            .password("test")
            .passwordRe("test")
            .build();

        bansikeeUserService.signUp(dto);

        BansikeeUser user = userRepository.findByEmail("test@test.com").get();

        boolean isValidPassword = passwordEncoder.matches("test", user.getPassword());
        assertThat(user.getEmail()).isEqualTo("test@test.com");
        assertThat(isValidPassword).isTrue();
        assertThat(user.getUsername()).isEqualTo("test");
    }

    @Transactional
    @DisplayName("로그인 테스트")
    @Test
    void signInTest() {
        BasicLoginUserRequest dto = BasicLoginUserRequest.builder()
            .email("test@test.com")
            .name("test")
            .password("test")
            .passwordRe("test")
            .build();
        bansikeeUserService.signUp(dto);
        SignInRequestDto signInDto = new SignInRequestDto("test@test.com", "test");
        SignInResponseDto signInResponseDto = bansikeeUserService.signIn(signInDto);

        assertThat(signInResponseDto.getEmail()).isEqualTo("test@test.com");
        assertThat(signInResponseDto.getName()).isEqualTo("test");
    }

    @Transactional
    @DisplayName("이미 회원가입된 이메일이면 예외를 발생한다.")
    @Test
    void signUpExceptionIfExistEmailTest() {
        BasicLoginUserRequest dto = BasicLoginUserRequest.builder()
            .email("test@test.com")
            .name("test")
            .password("test")
            .passwordRe("test")
            .build();

        bansikeeUserService.signUp(dto);

        assertThrows(AlreadySignedUpException.class, () -> bansikeeUserService.signUp(dto));
    }

    @Transactional
    @DisplayName("로그인시 비밀번호가 틀리면 예외가 발생한다.")
    @Test
    void signInFailTest() {
        BasicLoginUserRequest dto = BasicLoginUserRequest.builder()
            .email("test@test.com")
            .name("test")
            .password("test")
            .passwordRe("test")
            .build();
        bansikeeUserService.signUp(dto);

        SignInRequestDto signInDto = new SignInRequestDto("test@test.com", "no");
        assertThrows(InvalidPasswordException.class, () -> bansikeeUserService.signIn(signInDto));
    }

    @Transactional
    @DisplayName("일반로그인시 소셜회원이라면 틀리면 예외가 발생한다.")
    @Test
    void signInFailByOAuthTest() {
        TokenRequestDto dto = new TokenRequestDto(kakaoToken);
        KakaoProfile profile = (KakaoProfile) kakaoOAuthService.getProfile(dto);

        SignInRequestDto signInDto = new SignInRequestDto("cmg1411@naver.com", "no");
        assertThrows(HaveToSignInWithOauthException.class, () -> bansikeeUserService.signIn(signInDto));
    }

    @Transactional
    @DisplayName("email 로 회원을 찾는다.")
    @Test
    void findByEmailTest() {
        BasicLoginUserRequest dto = BasicLoginUserRequest.builder()
            .email("test@test.com")
            .name("test")
            .password("test")
            .passwordRe("test")
            .build();
        bansikeeUserService.signUp(dto);

        BansikeeUser byEmailService = bansikeeUserService.findByEmailService("test@test.com");

        assertThat(byEmailService.getEmail()).isEqualTo("test@test.com");
        assertThat(byEmailService.getUsername()).isEqualTo("test");
    }

    @Transactional
    @DisplayName("email 로 회원을 찾을때 존재하지 않는 이메일이면 예외를 발생한다.")
    @Test
    void findByNotExistEmailTest() {
        assertThrows(NotRegisteredEmailException.class, () -> bansikeeUserService.findByEmailService("test@test.com"));
    }

    @Transactional
    @DisplayName("닉네임 중복체크")
    @Test
    void checkNickNameDuplicateTest() {
        BasicLoginUserRequest dto = BasicLoginUserRequest.builder()
            .email("test@test.com")
            .name("test")
            .password("test")
            .passwordRe("test")
            .build();

        NickNameRequestDto nickNameRequestDto = new NickNameRequestDto("test");

        assertThat(bansikeeUserService.nickNameCheck(nickNameRequestDto)).isFalse();
        bansikeeUserService.signUp(dto);
        assertThat(bansikeeUserService.nickNameCheck(nickNameRequestDto)).isTrue();
    }

    @Transactional
    @Test
    void isOnBoardTest() {
        BasicLoginUserRequest dto = BasicLoginUserRequest.builder()
            .email("test@test.com")
            .name("test")
            .password("test")
            .passwordRe("test")
            .build();
        bansikeeUserService.signUp(dto);

        BansikeeUser user = userRepository.findByEmail("test@test.com").get();

        Boolean isOnboarded = bansikeeUserService.getIsOnboardedService(user.getId());
        assertThat(isOnboarded).isFalse();
    }
}