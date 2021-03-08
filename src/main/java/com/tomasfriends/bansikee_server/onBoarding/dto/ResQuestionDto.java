package com.tomasfriends.bansikee_server.onBoarding.dto;

import com.tomasfriends.bansikee_server.onBoarding.domain.Options;
import com.tomasfriends.bansikee_server.onBoarding.domain.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResQuestionDto {

    private Integer questionIdx;
    private String questionContent;
    private List<Options> resOptionList = new ArrayList<>();

    public static ResQuestionDto of(Question question){
        ResQuestionDto resQuestionDto = new ResQuestionDto();

        resQuestionDto.questionIdx = question.getQuestionIdx();
        resQuestionDto.questionContent = question.getContent();
        resQuestionDto.resOptionList = question.getOptions();

        return resQuestionDto;
    }

}
