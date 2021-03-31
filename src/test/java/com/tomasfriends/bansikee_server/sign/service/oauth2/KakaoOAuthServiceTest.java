package com.tomasfriends.bansikee_server.sign.service.oauth2;

import com.google.gson.Gson;
import com.tomasfriends.bansikee_server.sign.service.dto.KakaoProfile;
import com.tomasfriends.bansikee_server.sign.service.dto.TokenRequestDto;
import com.tomasfriends.bansikee_server.sign.service.exceptions.CommunicationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class KakaoOAuthServiceTest extends OAuthTokenToTest {

    private final KakaoOAuthService kakaoOAuthService = new KakaoOAuthService(new Gson());

    @Test
    @DisplayName("카카오 토큰으로 정보 받아오기")
    void kakaoTest() {
        // given
        TokenRequestDto dto = new TokenRequestDto(kakaoToken);

        // when
        KakaoProfile profile = (KakaoProfile) kakaoOAuthService.getProfile(dto);

        // then
        assertThat(profile.getKakao_account().getEmail()).isEqualTo("cmg1411@naver.com");
    }

    @Test
    @DisplayName("토큰이 유요하지 않으면 예외가 발생한다.")
    void invalidKakaoTokenTest() {
        // given
        String token = "1TFTYjeCyKkhrocWHe2ehtQHmHZE5OrmPaY_3dwo9c5sAAAF4h97nIA";
        TokenRequestDto dto = new TokenRequestDto(token);

        // then
        assertThrows(CommunicationException.class, () -> kakaoOAuthService.getProfile(dto));
    }
}