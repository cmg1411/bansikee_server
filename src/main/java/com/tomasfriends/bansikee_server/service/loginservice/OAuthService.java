package com.tomasfriends.bansikee_server.service.loginservice;

import com.tomasfriends.bansikee_server.dto.oauthprofile.Profile;

public interface OAuthService {
    Profile getProfile(String accessToken);
}
