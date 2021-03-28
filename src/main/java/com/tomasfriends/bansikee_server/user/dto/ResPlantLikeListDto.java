package com.tomasfriends.bansikee_server.user.dto;

import com.tomasfriends.bansikee_server.user.domain.FavoritesUser;
import com.tomasfriends.bansikee_server.user.domain.PlantUser;
import lombok.Data;

@Data
public class ResPlantLikeListDto {
    private Integer plantIdx;

    private String plantImgUrl;

    private String name;

    private String species;

    private String info;

    private boolean like;

    public static ResPlantLikeListDto of(FavoritesUser favoritesUser){
        ResPlantLikeListDto resPlantLikeListDto = new ResPlantLikeListDto();

        resPlantLikeListDto.plantIdx = favoritesUser.getPlant().getPlantIdx();
        resPlantLikeListDto.plantImgUrl = favoritesUser.getPlant().getPlantImgUrl();
        resPlantLikeListDto.name = favoritesUser.getPlant().getName();
        resPlantLikeListDto.species = favoritesUser.getPlant().getSpecies();
        resPlantLikeListDto.info = favoritesUser.getPlant().getInfo();

        return resPlantLikeListDto;

    }
}
