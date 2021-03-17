package com.tomasfriends.bansikee_server.onBoarding.dto;

import com.tomasfriends.bansikee_server.onBoarding.domain.Options;
import lombok.Data;

@Data
public class ResOptionDto {

    private Integer optionIdx;
    private String optionContent;

    public static ResOptionDto of(Options options){
        ResOptionDto resOptionDto = new ResOptionDto();

        resOptionDto.optionIdx = options.getOptionsIdx();
        resOptionDto.optionContent = options.getContent();

        return resOptionDto;
    }
}
