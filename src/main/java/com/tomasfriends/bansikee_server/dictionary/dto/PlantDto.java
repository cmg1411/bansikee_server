package com.tomasfriends.bansikee_server.dictionary.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name="item")
public class PlantDto {


    //species - 과명
    private String fmlCodeNm;

    //info - 특별관리 정보
    private String speclmanageInfo;

    //managementLevel
    private String managedemanddoCodeNm;

    //height
    private Integer growthHgInfo;

    //width
    private Integer growthAraInfo;

    //speed
    private String grwtveCodeNm;

    //smell
    private String smellCodeNm;

    //temperature
    private String grwhTpCodeNm;

    //water
    private String watercycleSprngCodeNm;

    //light
    private String lighttdemanddoCodeNm;

    //area
    private String postngplaceCodeNm;

}
