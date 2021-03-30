package com.tomasfriends.bansikee_server.mypage.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DiaryPictureTest {

    @Test
    void diaryPictureConstructByBuilderTest() {
        // given
        DiaryPicture diaryPicture1;
        Diary diary = new Diary();
        DiaryPicture diaryPicture2 = new DiaryPicture(diary, "picture");

        // when
        diaryPicture1 = DiaryPicture.builder()
            .diary(diary)
            .pictureUrl("picture")
            .build();

        // then
        assertThat(diaryPicture2.getDiary()).isEqualTo(diaryPicture1.getDiary());
        assertThat(diaryPicture2.getPictureUrl()).isEqualTo(diaryPicture1.getPictureUrl());
    }
}