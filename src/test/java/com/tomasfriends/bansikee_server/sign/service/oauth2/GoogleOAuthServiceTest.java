package com.tomasfriends.bansikee_server.sign.service.oauth2;

import com.tomasfriends.bansikee_server.sign.service.dto.GoogleProfile;
import com.tomasfriends.bansikee_server.sign.service.dto.Profile;
import com.tomasfriends.bansikee_server.sign.service.dto.TokenRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class GoogleOAuthServiceTest {

    private GoogleOAuthService googleOAuthService = new GoogleOAuthService();

    @Test
    @DisplayName("구글토큰으로 정보 받아오기")
    void googleTest() {
        // given
        String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjZhOGJhNTY1MmE3MDQ0MTIxZDRmZWRhYzhmMTRkMTRjNTRlNDg5NWIiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI5ODI1NDY5NTU3Ni1rdXE1YzJjNDFzbGc3bDMyZG42bDBhODBxcDRhdjJlOS5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsImF1ZCI6Ijk4MjU0Njk1NTc2LWdvMjZvMXQ5MmZmbTJscWduN2p0aGYzMmYzcDcwMWtzLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwic3ViIjoiMTAyNTU2NDEyMTA5NzU1ODk5MTg0IiwiZW1haWwiOiJiYWd0YWVodW5AZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsIm5hbWUiOiJ0eWVob29uZXkiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1NaktkVUJrRk1MWS9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BTVp1dWNrejBJMF9xcnYwRktPWHZiN1Q4LWZVTHlVMjJRL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJ0eWVob29uZXkiLCJsb2NhbGUiOiJrbyIsImlhdCI6MTYxNjQ4NDE0NiwiZXhwIjoxNjE2NDg3NzQ2fQ.o3uwzB5NmYS1VLQK4n5HuUq87BY6CEYd6LQivQalfcg1TjqjrFZYa4nSkhvDl7D35Qp88LTmzMVIvs7qH8S9drmXdYHPddbog5cTjDKje5MK1XDC_tBat_ubOFGdix9J9MJo2kg8Anc7XT0p0hnr_gk--aDHqdoFbC_NXj1nbbO4PJX5ZmdEuIgDASMrZ-Sq5YBM-NCdi5K4relUSu47yJqgOrDB1u-rRqZtfWbunQoG-UtPnclsIW7_xW9gGdLtbJJZ4WiunqQQa9msURoLzUGH1pFmeg_gqcW3QX67CksTGI1by0AgIvL-DKaQdFjB5nzJX8_9uMl6nwhDoz2U9Q";
        TokenRequestDto dto = new TokenRequestDto(token);

        // when
        Profile profile = googleOAuthService.getProfile(dto);

        // then
        Assertions.assertThat(profile).isNotNull();
    }

    @Test
    void restTemplateTest() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjZhOGJhNTY1MmE3MDQ0MTIxZDRmZWRhYzhmMTRkMTRjNTRlNDg5NWIiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI5ODI1NDY5NTU3Ni1rdXE1YzJjNDFzbGc3bDMyZG42bDBhODBxcDRhdjJlOS5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsImF1ZCI6Ijk4MjU0Njk1NTc2LWdvMjZvMXQ5MmZmbTJscWduN2p0aGYzMmYzcDcwMWtzLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwic3ViIjoiMTAyNTU2NDEyMTA5NzU1ODk5MTg0IiwiZW1haWwiOiJiYWd0YWVodW5AZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsIm5hbWUiOiJ0eWVob29uZXkiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1NaktkVUJrRk1MWS9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BTVp1dWNrejBJMF9xcnYwRktPWHZiN1Q4LWZVTHlVMjJRL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJ0eWVob29uZXkiLCJsb2NhbGUiOiJrbyIsImlhdCI6MTYxNjQ4NDE0NiwiZXhwIjoxNjE2NDg3NzQ2fQ.o3uwzB5NmYS1VLQK4n5HuUq87BY6CEYd6LQivQalfcg1TjqjrFZYa4nSkhvDl7D35Qp88LTmzMVIvs7qH8S9drmXdYHPddbog5cTjDKje5MK1XDC_tBat_ubOFGdix9J9MJo2kg8Anc7XT0p0hnr_gk--aDHqdoFbC_NXj1nbbO4PJX5ZmdEuIgDASMrZ-Sq5YBM-NCdi5K4relUSu47yJqgOrDB1u-rRqZtfWbunQoG-UtPnclsIW7_xW9gGdLtbJJZ4WiunqQQa9msURoLzUGH1pFmeg_gqcW3QX67CksTGI1by0AgIvL-DKaQdFjB5nzJX8_9uMl6nwhDoz2U9Q";
        String url = "https://oauth2.googleapis.com/tokeninfo?id_token=" + token;
        GoogleProfile profile = restTemplate.getForObject(url, GoogleProfile.class);
        System.out.println("profile = " + profile.toString());
        Assertions.assertThat(profile).isNotNull();
    }
}