package com.tomasfriends.bansikee_server.mypage.domain;

import com.tomasfriends.bansikee_server.common.AuthenticationUser;
import com.tomasfriends.bansikee_server.mypage.domain.exceptions.IllegalAuthUserException;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;

public interface MyPlant {

    default BansikeeUser checkAuth(Integer contentsId) {
        BansikeeUser authenticationUser = AuthenticationUser.getAuthenticationUser();
        isAuthUser(authenticationUser.getId(), contentsId);
        return authenticationUser;
    }

    private void isAuthUser(Integer userId, Integer contentsId) {
        if (!userId.equals(contentsId)) {
            throw new IllegalAuthUserException();
        }
    }
}
