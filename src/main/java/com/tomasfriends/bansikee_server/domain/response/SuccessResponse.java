package com.tomasfriends.bansikee_server.domain.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SuccessResponse {

    private int status;
    private String title;
    private String detail;
}
