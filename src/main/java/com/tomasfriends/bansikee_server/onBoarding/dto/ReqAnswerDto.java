package com.tomasfriends.bansikee_server.onBoarding.dto;

import com.tomasfriends.bansikee_server.onBoarding.domain.OnBoardingAnswer;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ReqAnswerDto {

    @NotBlank
    private int questionIdx;

    @NotBlank
    private int optionIdx;


}
