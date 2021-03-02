package com.tomasfriends.bansikee_server.service.loginservice;

import com.tomasfriends.bansikee_server.domain.login.profile.Profile;

public interface OAuthService {
    Profile getProfile(String accessToken);
}
