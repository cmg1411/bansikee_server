package com.tomasfriends.bansikee_server.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data

public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionIdx;

    private String content;

}
