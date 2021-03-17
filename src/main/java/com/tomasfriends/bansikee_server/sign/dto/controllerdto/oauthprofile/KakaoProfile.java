package com.tomasfriends.bansikee_server.sign.dto.controllerdto.oauthprofile;

import com.tomasfriends.bansikee_server.sign.domain.LoginMethod;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoProfile implements Profile {
    private int id;
    private Properties properties;
    private KakaoAccount kakao_account;

    @Override
    public LoginMethod getLoginMethod() {
        return LoginMethod.KAKAO;
    }

    @Getter
    @Setter
    @ToString
    public static class Properties {
        private String nickname;
        private String profile_image;
    }

    @Getter
    @Setter
    @ToString
    public static class KakaoAccount {
        private String email;
    }
}
