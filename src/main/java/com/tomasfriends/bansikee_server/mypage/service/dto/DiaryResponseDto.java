package com.tomasfriends.bansikee_server.mypage.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.tomasfriends.bansikee_server.mypage.service.dto.DiaryRequestDto.*;

@Getter
@Builder
@AllArgsConstructor
public class DiaryResponseDto {

    private int myDiaryId;
    private PictureUrls diaryPictures;
    private String nickName;
    private Weather weather;
    private Watered watered;
    private double height;
    private LocalDateTime writeDate;
    private String contents;
}
