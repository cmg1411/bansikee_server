package com.tomasfriends.bansikee_server.response.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListDataSuccessResponse<T> extends SuccessResponse {
    private List<T> listData;
}
