package com.tomasfriends.bansikee_server.onBoarding.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class User {

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


}
