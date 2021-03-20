package com.tomasfriends.bansikee_server.mypage.domain;

import com.tomasfriends.bansikee_server.common.BaseEntity;
import com.tomasfriends.bansikee_server.mypage.service.dto.PlantRegistrationRequestDto;
import com.tomasfriends.bansikee_server.onBoarding.domain.Plant;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx")
    private BansikeeUser user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plantIdx")
    private Plant plant;

    @Builder
    public PlantRegistration(String pictureUrl, Plant plant, String plantNickName, LocalDateTime plantBirth, String plantIntroduce, Integer wateringCycle, BansikeeUser user) {
        this.pictureUrl = pictureUrl;
        this.plant = plant;
        this.plantNickName = plantNickName;
        this.plantBirth = plantBirth;
        this.plantIntroduce = plantIntroduce;
        this.wateringCycle = wateringCycle;
        this.user = user;
    }
}
