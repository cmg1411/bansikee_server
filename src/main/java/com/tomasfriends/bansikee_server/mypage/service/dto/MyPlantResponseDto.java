package com.tomasfriends.bansikee_server.mypage.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class MyPlantResponseDto {

    private int myPlantId;
    private String plantName;
    private String nickName;
    private int water;
    private String myPlantProfileUrl;
    private String contents;
    private long dDay;
    private LocalDateTime startDate;
}
