package com.tomasfriends.bansikee_server.sign.service.oauth2;

import com.google.gson.Gson;
import com.tomasfriends.bansikee_server.sign.service.dto.GoogleProfile;
import com.tomasfriends.bansikee_server.sign.service.dto.KakaoProfile;
import com.tomasfriends.bansikee_server.sign.service.dto.SignInResponseDto;
import com.tomasfriends.bansikee_server.sign.service.dto.TokenRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class OauthSignUpServiceTest extends OAuthTokenToTest {

    @Autowired private OauthSignUpService oauthSignUpService;
    private final KakaoOAuthService kakaoOAuthService = new KakaoOAuthService(new Gson());
    private final GoogleOAuthService googleOAuthService = new GoogleOAuthService();

    @DisplayName("카카오 프로필이 주어지면 로그인을 하여 엑세스 토큰을 발급한다.")
    @Test
    void kakaoSignInTest() {
        TokenRequestDto dto = new TokenRequestDto(kakaoToken);
        KakaoProfile profile = (KakaoProfile) kakaoOAuthService.getProfile(dto);
        SignInResponseDto signInResponseDto = oauthSignUpService.signUpWithKakao(profile);

        assertThat(signInResponseDto.getJwt()).isNotNull();
        assertThat(signInResponseDto.getEmail()).isEqualTo("cmg1411@naver.com");
    }

    @DisplayName("구글 프로필이 주어지면 로그인을 하여 엑세스 토큰을 발급한다.")
    @Test
    void googleSignInTest() {
        TokenRequestDto dto = new TokenRequestDto(googleToken);
        GoogleProfile profile = (GoogleProfile) googleOAuthService.getProfile(dto);
        SignInResponseDto signInResponseDto = oauthSignUpService.signUpWithGoogle(profile);

        assertThat(signInResponseDto.getJwt()).isNotNull();
        assertThat(signInResponseDto.getEmail()).isEqualTo("cmg14111@gmail.com");
    }
}