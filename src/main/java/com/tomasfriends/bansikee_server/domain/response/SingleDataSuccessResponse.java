package com.tomasfriends.bansikee_server.domain.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleDataSuccessResponse<T> extends SuccessResponse {
    private T data;
}
