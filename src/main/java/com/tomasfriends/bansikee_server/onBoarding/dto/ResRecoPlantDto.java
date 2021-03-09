package com.tomasfriends.bansikee_server.onBoarding.dto;

import com.tomasfriends.bansikee_server.onBoarding.domain.Plant;
import lombok.Data;

@Data
public class ResRecoPlantDto {

    private Integer plantIdx;

    private String plantImgUrl;

    private String name;

    private String species;

    private String info;

    private boolean like;

    private Integer count;

    public static ResRecoPlantDto of(Plant plant){

        ResRecoPlantDto resRecoPlantDto = new ResRecoPlantDto();

        resRecoPlantDto.plantIdx = plant.getPlantIdx();
        resRecoPlantDto.plantImgUrl = plant.getPlantImgUrl();
        resRecoPlantDto.name = plant.getName();
        resRecoPlantDto.species = plant.getSpecies();
        resRecoPlantDto.info = plant.getInfo();

        return resRecoPlantDto;
    }

}
