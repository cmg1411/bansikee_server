package com.tomasfriends.bansikee_server.mypage.service.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class MyPlantResponseDto {

    private final int myPlantId;
    private final int plantId;
    private final String plantName;
    private final String nickName;
    private final int water;
    private final String myPlantProfileUrl;
    private final String contents;
    private final long dDay;
    private final String plantTip;
    private final LocalDateTime startDate;
}
