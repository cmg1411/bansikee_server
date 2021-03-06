package com.tomasfriends.bansikee_server.onBoarding.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Options {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer optionsIdx;

//    private Integer questionIdx;

    private String content;
}
