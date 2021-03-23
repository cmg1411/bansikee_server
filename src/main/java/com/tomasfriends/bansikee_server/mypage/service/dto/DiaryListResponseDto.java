package com.tomasfriends.bansikee_server.mypage.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DiaryListResponseDto {

    private String diaryProfile;
    private LocalDate writeDate;
}
