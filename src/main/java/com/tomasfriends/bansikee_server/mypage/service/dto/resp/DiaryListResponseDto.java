package com.tomasfriends.bansikee_server.mypage.service.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DiaryListResponseDto {

    private final int diaryId;
    private final String diaryProfile;
    private final LocalDate writeDate;
}
