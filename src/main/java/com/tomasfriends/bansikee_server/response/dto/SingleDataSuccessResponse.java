package com.tomasfriends.bansikee_server.response.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleDataSuccessResponse<T> extends SuccessResponse {
    private T data;
}
