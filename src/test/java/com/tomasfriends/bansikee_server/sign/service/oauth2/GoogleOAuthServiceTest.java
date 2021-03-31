package com.tomasfriends.bansikee_server.sign.service.oauth2;

import com.tomasfriends.bansikee_server.sign.service.dto.GoogleProfile;
import com.tomasfriends.bansikee_server.sign.service.dto.TokenRequestDto;
import com.tomasfriends.bansikee_server.sign.service.exceptions.CommunicationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GoogleOAuthServiceTest extends OAuthTokenToTest {

    private final GoogleOAuthService googleOAuthService = new GoogleOAuthService();

    @Test
    @DisplayName("구글토큰으로 정보 받아오기")
    void googleTest() {
        // given
        TokenRequestDto dto = new TokenRequestDto(googleToken);

        // when
        GoogleProfile profile = (GoogleProfile) googleOAuthService.getProfile(dto);

        // then
        assertThat(profile.getEmail()).isEqualTo("cmg14111@gmail.com");
    }

    @Test
    @DisplayName("토큰이 유요하지 않으면 예외가 발생한다.")
    void invalidGoogleTokenTest() {
        // given
        String token =
            "1eyJhbGciOiJSUzI1NiIsImtpZCI6IjEzZThkNDVhNDNjYjIyNDIxNTRjN2Y0ZGFmYWMyOTMzZmVhMjAzNzQiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI5ODI1NDY5NTU3Ni00M2tmODhmZTUzOXFsaWo3cnZlZjNlZ21pMWJubGV0dS5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsImF1ZCI6Ijk4MjU0Njk1NTc2LTQza2Y4OGZlNTM5cWxpajdydmVmM2VnbWkxYm5sZXR1LmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwic3ViIjoiMTExOTcyMjY0NTc1NDc1NTM1NDA1IiwiZW1haWwiOiJjbWcxNDExMUBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiYXRfaGFzaCI6IktfWWNBdl9kWnBYSXVRQjhXUnpUUVEiLCJuYW1lIjoicm9ybyBwbyIsInBpY3R1cmUiOiJodHRwczovL2xoNC5nb29nbGV1c2VyY29udGVudC5jb20vLWxMNDZKcDF3MFNFL0FBQUFBQUFBQUFJL0FBQUFBQUFBQUFBL0FNWnV1Y2tzdmZpMGdVRFVkMTBaYzVzSGhiR3NaNGhBd3cvczk2LWMvcGhvdG8uanBnIiwiZ2l2ZW5fbmFtZSI6InJvcm8iLCJmYW1pbHlfbmFtZSI6InBvIiwibG9jYWxlIjoia28iLCJpYXQiOjE2MTcxODcwMTYsImV4cCI6MTYxNzE5MDYxNn0.Ou1Nxn0AD8AcQj3bjPVs-QDxEPFK0WscPryYIw3yxhLqvy9YQjvQh7jp70VCmvMebAkMZKeAdjugaTt0MKcTkoZP7uy6Q-QYhTp4RSqGukAI1S4vgXwho1pHyDIOT_qDoZV0l20n_Uz9s61nItXZfjIbT1pnXEww7y3J_N5OULdz05fMXvgTXPttJRSHz5P3b0wdKhd61stXEG00XFg60ue6S0k1I7thVlFYJxo3fme_1lwA18Zldu9Dyl7GeYFnPDuTLwMwMhxTfUVvk2OADypHMgIUJUnRMuy21tI20kAOZ-DRjhXAo1O_Z0SHchPBpA1z3dQ6_dO951MqprNsnQ";
        TokenRequestDto dto = new TokenRequestDto(token);

        // then
        assertThrows(CommunicationException.class, () -> googleOAuthService.getProfile(dto));
    }
}