package com.tomasfriends.bansikee_server.domain.login.profile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoogleProfile implements Profile {
    private String email;
    private String name;
    private String picture;
    private String given_name;
    private int iat;
    private int exp;
}
