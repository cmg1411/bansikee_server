package com.tomasfriends.bansikee_server.dictionary.dto.dictionaryDto;

import lombok.Data;

@Data
public class ResPlantListDto {
    private Integer plantIdx;

    private String plantImgUrl;

    private String name;

    private String species;

    private String info;

    private boolean like;
}
