package com.tomasfriends.bansikee_server.onBoarding.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReqAnswerListDto {

    private List<ReqAnswerDto> reqAnswerList = new ArrayList<>();

}
