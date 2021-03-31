package com.tomasfriends.bansikee_server.user.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user")
public class UserMyPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer useridx;

    private String email;

    private String loginMethod;

    private String name;

    @Column(columnDefinition = "boolean default false")
    private Integer onBoarding;

    private String password;

    private String userimageurl;

    public void update(String name, String userimageurl) {
        this.name = name;
        this.userimageurl = userimageurl;
    }
}
