package com.tomasfriends.bansikee_server.onBoarding.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer plantIdx;

    private String name;

    private String species;

    private String info;

    private Integer managementLevel;

    private Integer height;

    private Integer width;

    private String speed;

    private String smell;

    private String temperature;

    private String water;

    private String light;

    private String area;

    private String plantImgUrl;
}
