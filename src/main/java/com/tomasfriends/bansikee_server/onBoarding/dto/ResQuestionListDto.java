package com.tomasfriends.bansikee_server.onBoarding.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResQuestionListDto {

    private List<ResQuestionDto> resQuestionList = new ArrayList<>();

    public void addQuestionDto(ResQuestionDto resQuestionDto) {
        resQuestionList.add(resQuestionDto);
    }



}
