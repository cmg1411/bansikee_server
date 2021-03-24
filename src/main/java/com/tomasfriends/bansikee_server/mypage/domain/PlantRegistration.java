package com.tomasfriends.bansikee_server.mypage.domain;

import com.tomasfriends.bansikee_server.common.BaseEntity;
import com.tomasfriends.bansikee_server.mypage.service.dto.resp.MyPlantListResponseDto;
import com.tomasfriends.bansikee_server.mypage.service.dto.resp.MyPlantResponseDto;
import com.tomasfriends.bansikee_server.onBoarding.domain.Plant;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "myPlant")
public class PlantRegistration extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "myPlantImgUrl")
    private String pictureUrl;

    @Column(name = "nickName")
    private String plantNickName;

    @Column(name = "startDate")
    private LocalDateTime plantBirth;

    @Column(name = "summary")
    private String plantIntroduce;

    @Column(name = "waterCycle")
    private Integer wateringCycle;

    private LocalDate lastWateredDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    private BansikeeUser user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plantIdx")
    private Plant plant;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "myPlant", orphanRemoval=true)
    private List<Diary> diaries;

    @Builder
    public PlantRegistration(Integer id, String pictureUrl, Plant plant, String plantNickName, LocalDateTime plantBirth, String plantIntroduce, Integer wateringCycle, BansikeeUser user) {
        this.id = id;
        this.pictureUrl = pictureUrl;
        this.plant = plant;
        this.plantNickName = plantNickName;
        this.plantBirth = plantBirth;
        this.plantIntroduce = plantIntroduce;
        this.wateringCycle = wateringCycle;
        this.user = user;
    }

    public MyPlantListResponseDto toListResponseDto() {
        Map<String, Long> waterDaysNotice = getWaterDaysNotice();
        return MyPlantListResponseDto.builder()
            .myPlantId(id)
            .nickName(plantNickName)
            .contents(plantIntroduce)
            .plantName(plant.getName())
            .profileImgUrl(pictureUrl)
            .waterDaysFrom(waterDaysNotice.get("from"))
            .waterDaysTo(waterDaysNotice.get("to"))
            .build();
    }

    public Map<String, Long> getWaterDaysNotice() {
        if (lastWateredDate == null) {
            return Map.of("from", -1L, "to", -1L);
        }
        long from = getDDay(lastWateredDate.atStartOfDay());
        long to = wateringCycle - from;
        return Map.of("from", from, "to", to);
    }

    public MyPlantResponseDto toMyPlantResponseDto() {
        return MyPlantResponseDto.builder()
            .myPlantId(id)
            .plantName(plant.getName())
            .nickName(plantNickName)
            .water(wateringCycle)
            .contents(plantIntroduce)
            .myPlantProfileUrl(pictureUrl)
            .startDate(plantBirth)
            .plantTip(plant.getInfo())
            .dDay(getDDay(plantBirth))
            .build();
    }

    public long getDDay(LocalDateTime date) {
        return ChronoUnit.DAYS.between(date.toLocalDate(), LocalDate.now());
    }
}