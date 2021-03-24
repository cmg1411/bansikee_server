package com.tomasfriends.bansikee_server.mypage.service.dto.resp;

import com.tomasfriends.bansikee_server.mypage.service.dto.PictureUrls;
import com.tomasfriends.bansikee_server.mypage.service.dto.Watered;
import com.tomasfriends.bansikee_server.mypage.service.dto.Weather;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class DiaryResponseDto {

    private final int myDiaryId;
    private final PictureUrls diaryPictures;
    private final String nickName;
    private final Weather weather;
    private final Watered watered;
    private final double height;
    private final LocalDateTime writeDate;
    private final String contents;
}
