package com.tomasfriends.bansikee_server.onBoarding.domain;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.JoinColumn;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionIdx;

    private String content;

    private String info;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "options")
    @JoinColumn(name = "questionIdx")
    private List <Options> options = new ArrayList<>();

}
