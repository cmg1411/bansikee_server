package com.tomasfriends.bansikee_server.dictionary.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "plant")
public class PlantDictionary {

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "favorites")
    @JoinColumn(name = "plantIdx")
    private List<FavoritesDictionary> favorites = new ArrayList<>();

    public static PlantDictionary of( String name, String species, String info, String managementLevel, Integer height,Integer width,String speed,String smell,String temperature,String summerWater,String winterWater,String light,String area, String plantImgUrl, Integer managementLevelCode, Integer heightCode, Integer speedCode,Integer smellCode) {
        PlantDictionary plantDictionary = new PlantDictionary();

        plantDictionary.name = name;
        plantDictionary.species = species;
        plantDictionary.info = info;
        plantDictionary.managementLevel = managementLevel;
        plantDictionary.height = height;
        plantDictionary.width = width;
        plantDictionary.speed = speed;
        plantDictionary.smell = smell;
        plantDictionary.temperature = temperature;
        plantDictionary.summerWater = summerWater;
        plantDictionary.winterWater = winterWater;
        plantDictionary.light = light;
        plantDictionary.area = area;
        plantDictionary.plantImgUrl = plantImgUrl;
        plantDictionary.managementLevelCode = managementLevelCode;
        plantDictionary.heightCode = heightCode;
        plantDictionary.speedCode = speedCode;
        plantDictionary.smellCode = smellCode;
        return plantDictionary;
    }
}
