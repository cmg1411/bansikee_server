package com.tomasfriends.bansikee_server.dictionary.dto.dictionaryDto;

import com.tomasfriends.bansikee_server.dictionary.domain.PlantDictionary;
import lombok.Data;

@Data
public class ResPlantDto {

    private Integer plantIdx;

    private String plantImgUrl;

    private String name;

    private String species;

    private boolean like;

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


    public static ResPlantDto of(PlantDictionary plantDictionary){
        ResPlantDto resPlantDto = new ResPlantDto();

        resPlantDto.plantIdx = plantDictionary.getPlantIdx();
        resPlantDto.plantImgUrl = plantDictionary.getPlantImgUrl();
        resPlantDto.name = plantDictionary.getName();
        resPlantDto.species = plantDictionary.getSpecies();
        resPlantDto.info = plantDictionary.getInfo();
        resPlantDto.managementLevel = plantDictionary.getManagementLevel();
        resPlantDto.height = plantDictionary.getHeight();
        resPlantDto.width = plantDictionary.getWidth();
        resPlantDto.speed = plantDictionary.getSpeed();
        resPlantDto.smell = plantDictionary.getSmell();
        resPlantDto.temperature = plantDictionary.getTemperature();
        resPlantDto.summerWater = plantDictionary.getSummerWater();
        resPlantDto.winterWater = plantDictionary.getWinterWater();
        resPlantDto.light = plantDictionary.getLight();
        resPlantDto.area = plantDictionary.getArea();
        return resPlantDto;
    }

}
