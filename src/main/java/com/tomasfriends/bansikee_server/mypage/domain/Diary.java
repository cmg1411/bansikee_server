package com.tomasfriends.bansikee_server.mypage.domain;

import com.tomasfriends.bansikee_server.common.BaseEntity;
import com.tomasfriends.bansikee_server.mypage.service.dto.*;
import com.tomasfriends.bansikee_server.mypage.service.dto.resp.DiaryListResponseDto;
import com.tomasfriends.bansikee_server.mypage.service.dto.resp.DiaryResponseDto;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Diary extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    public DiaryListResponseDto toListResponseDto(int diaryId, DiaryPicture diaryProfilePicture, LocalDate writeDate) {
        return new DiaryListResponseDto(diaryId, diaryProfilePicture.getPictureUrl(), writeDate);
    }

    public DiaryResponseDto toDiaryResponseDto() {
        return DiaryResponseDto.builder()
            .myDiaryId(id)
            .dayFromBirth(PlantRegistration.getDDay(myPlant.getPlantBirth()))
            .diaryPictures(getDiaryPictures(pictures))
            .nickName(myPlant.getPlantNickName())
            .weather(weather)
            .watered(water)
            .height(height)
            .writeDate(getCreatedDate())
            .contents(contents)
            .build();
    }

    private PictureUrls getDiaryPictures(List<DiaryPicture> pictures) {
        return new PictureUrls(getPictureCollect(pictures));
    }

    private List<String> getPictureCollect(List<DiaryPicture> pictures) {
        if (Objects.isNull(pictures)) {
            return Collections.emptyList();
        }
        return pictures.stream().map(DiaryPicture::getPictureUrl).collect(toList());
    }

    public DiaryPicture getFirstDiaryPicture() {
        if (pictures.isEmpty()) {
            return new DiaryPicture(this, "사진을 등록하지 않았습니다.");
        }
        return pictures.get(0);
    }
}








