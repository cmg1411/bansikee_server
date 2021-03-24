package com.tomasfriends.bansikee_server.mypage.service.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MyPlantListResponseDto {

    private final Integer myPlantId;
    private final String nickName;
    private final String profileImgUrl;
    private final String plantName;
    private final String contents;
    private final long waterDaysFrom;
    private final long waterDaysTo;
}
