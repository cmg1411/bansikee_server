package com.tomasfriends.bansikee_server.mypage.domain;

import com.tomasfriends.bansikee_server.common.BaseEntity;
import com.tomasfriends.bansikee_server.mypage.service.dto.DiaryListResponseDto;
import com.tomasfriends.bansikee_server.mypage.service.dto.Watered;
import com.tomasfriends.bansikee_server.mypage.service.dto.Weather;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Diary extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Weather weather;

    private Integer height;

    @Enumerated(EnumType.STRING)
    private Watered water;

    private String contents;

    @ManyToOne
    @JoinColumn(name = "my_plant_id")
    private PlantRegistration myPlant;

    @OneToOne
    private BansikeeUser user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "diary", orphanRemoval=true)
    private List<DiaryPicture> pictures;

    @Builder
    public Diary(Weather weather, Integer height, Watered water, String contents, PlantRegistration myPlant, BansikeeUser user) {
        this.weather = weather;
        this.height = height;
        this.water = water;
        this.contents = contents;
        this.myPlant = myPlant;
        this.user = user;
    }

    public DiaryListResponseDto toListResponseDto(DiaryPicture diaryProfilePicture, LocalDate writeDate) {
        return new DiaryListResponseDto(diaryProfilePicture.getPictureUrl(), writeDate);
    }
}
