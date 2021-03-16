package com.tomasfriends.bansikee_server.sign.service.oauth2;

import com.google.gson.Gson;
import com.tomasfriends.bansikee_server.sign.service.dto.KakaoProfile;
import com.tomasfriends.bansikee_server.sign.service.dto.Profile;
import com.tomasfriends.bansikee_server.sign.service.dto.TokenRequestDto;
import com.tomasfriends.bansikee_server.sign.service.exceptions.CommunicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class KakaoOAuthService implements OAuthService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Gson gson;

    @Override
    public Profile getProfile(TokenRequestDto accessToken) {
        // Set header : Content-type: application/x-www-form-urlencoded
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + accessToken.getAccessToken());

        // Set http entity
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);
        try {
            // Request profile
            ResponseEntity<String> response
                = restTemplate.postForEntity("https://kapi.kakao.com/v2/user/me", request, String.class);
            if (response.getStatusCode() == HttpStatus.OK)
                return gson.fromJson(response.getBody(), KakaoProfile.class);
        } catch (Exception e) {
            throw new CommunicationException();
        }
        throw new CommunicationException();
    }
}