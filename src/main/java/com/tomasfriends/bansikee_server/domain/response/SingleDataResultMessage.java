package com.tomasfriends.bansikee_server.domain.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleDataResultMessage<T> extends ResultMessage {
    private T data;
}
