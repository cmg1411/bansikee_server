package com.tomasfriends.bansikee_server.domain.login.profile;

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
