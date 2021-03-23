package com.tomasfriends.bansikee_server.mypage.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MyPlantListResponseDto {

    private Integer MyPlantId;
    private String nickName;
    private String profileImgUrl;
    private String plantName;
    private String contents;
}
