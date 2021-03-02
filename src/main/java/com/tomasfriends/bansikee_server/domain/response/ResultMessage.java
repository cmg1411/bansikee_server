package com.tomasfriends.bansikee_server.domain.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResultMessage {

    @ApiModelProperty(value = "응답 성공여부 : true/false")
    private boolean success;

    @ApiModelProperty(value = "응답 코드 : (성공 ? 양수 : 음수)")
    private int code;

    @ApiModelProperty(value = "응답 메세지")
    private String msg;
}
