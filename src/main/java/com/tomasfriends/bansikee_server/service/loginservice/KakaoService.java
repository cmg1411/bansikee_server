package com.tomasfriends.bansikee_server.service.loginservice;

import com.google.gson.Gson;
import com.tomasfriends.bansikee_server.domain.login.profile.KakaoProfile;
import com.tomasfriends.bansikee_server.domain.login.profile.Profile;
import com.tomasfriends.exceptions.CommunicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class KakaoService implements OAuthService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Gson gson;

    @Override
    public Profile getProfile(String accessToken) {
        // Set header : Content-type: application/x-www-form-urlencoded
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + accessToken);

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