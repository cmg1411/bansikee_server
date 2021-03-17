package com.tomasfriends.bansikee_server.dictionary.dto.dictionaryDto;

import com.tomasfriends.bansikee_server.dictionary.domain.PlantDictionary;
import lombok.Data;

@Data
public class ResPlantListDto {
    private Integer plantIdx;

    private String plantImgUrl;

    private String name;

    private String species;

    private String info;

    private boolean like;

    public static ResPlantListDto of(PlantDictionary plantDictionary){

        ResPlantListDto resPlantListDto = new ResPlantListDto();

        resPlantListDto.plantIdx = plantDictionary.getPlantIdx();
        resPlantListDto.plantImgUrl = plantDictionary.getPlantImgUrl();
        resPlantListDto.name = plantDictionary.getName();
        resPlantListDto.species = plantDictionary.getSpecies();
        resPlantListDto.info = plantDictionary.getInfo();

        return resPlantListDto;
    }
}
