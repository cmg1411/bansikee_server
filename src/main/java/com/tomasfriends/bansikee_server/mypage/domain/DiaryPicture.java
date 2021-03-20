package com.tomasfriends.bansikee_server.mypage.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class DiaryPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "DIARY_ID")
    private Diary diary;

    private String pictureUrl;

    @Builder
    public DiaryPicture(Diary diary, String pictureUrl) {
        this.diary = diary;
        this.pictureUrl = pictureUrl;
    }
}
