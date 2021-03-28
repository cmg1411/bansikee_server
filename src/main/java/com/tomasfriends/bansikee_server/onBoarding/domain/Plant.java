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

    private String summerWater;

    private String winterWater;

    private String light;

    private String area;

    private String plantImgUrl;

    private Integer managementLevelCode;
    private Integer heightCode;
    private Integer speedCode;
    private Integer smellCode;

    public Plant() {
    }

    public Plant(Integer plantIdx, String name, String species, String info, String managementLevel, Integer height, Integer width, String speed, String smell, String temperature, String summerWater, String winterWater, String light, String area, String plantImgUrl, Integer managementLevelCode, Integer heightCode, Integer speedCode, Integer smellCode) {
        this.plantIdx = plantIdx;
        this.name = name;
        this.species = species;
        this.info = info;
        this.managementLevel = managementLevel;
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.smell = smell;
        this.temperature = temperature;
        this.summerWater = summerWater;
        this.winterWater = winterWater;
        this.light = light;
        this.area = area;
        this.plantImgUrl = plantImgUrl;
        this.managementLevelCode = managementLevelCode;
        this.heightCode = heightCode;
        this.speedCode = speedCode;
        this.smellCode = smellCode;
    }
}
