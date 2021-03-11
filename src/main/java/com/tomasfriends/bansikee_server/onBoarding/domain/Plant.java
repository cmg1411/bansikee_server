package com.tomasfriends.bansikee_server.onBoarding.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer plantIdx;

    private String name;

    private String species;

    private String info;

    private String managementLevel;

    private Integer height;

    private Integer width;

    private String speed;

    private String smell;

    private String temperature;

    private String water;

    private String light;

    private String area;

    private String plantImgUrl;

    private Integer managementLevelCode;
    private Integer heightCode;
    private Integer speedCode;
    private Integer smellCode;

}
