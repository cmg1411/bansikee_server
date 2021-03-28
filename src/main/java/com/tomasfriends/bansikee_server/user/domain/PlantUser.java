package com.tomasfriends.bansikee_server.user.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "plant")
public class PlantUser {
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

    private String summerWater;

    private String winterWater;

    private String light;

    private String area;

    private String plantImgUrl;

    private Integer managementLevelCode;
    private Integer heightCode;
    private Integer speedCode;
    private Integer smellCode;
}
