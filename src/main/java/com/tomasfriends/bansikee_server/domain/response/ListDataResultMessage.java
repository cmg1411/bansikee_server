package com.tomasfriends.bansikee_server.domain.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListDataResultMessage<T> extends ResultMessage {
    private List<T> listData;
}
