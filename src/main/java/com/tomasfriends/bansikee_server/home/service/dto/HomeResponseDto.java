package com.tomasfriends.bansikee_server.home.service.dto;

import com.tomasfriends.bansikee_server.mypage.domain.PlantRegistration;
import com.tomasfriends.bansikee_server.onBoarding.domain.Plant;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import lombok.Getter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.tomasfriends.bansikee_server.util.PlantImgUtil.getPlantProfileImg;

@Getter
public class HomeResponseDto {

    private int userId;
    private String userName;
    private String userProfile;
    private String greeting;
    private int recommendPlantId;
    private String recommendPlantImg;
    private String recommendPlantName;
    private String recommendPlantSpices;
    private String recommendPlantInfo;
    private List<HomePlant> myPlants;


    public void setUser(BansikeeUser user) {
        this.userId = user.getId();
        this.userName = user.getUsername();
        this.userProfile = user.getProfileImageURL();
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public void setRandomRecommendPlant(Plant randomPlant) {
        this.recommendPlantId = randomPlant.getPlantIdx();
        this.recommendPlantImg = getPlantProfileImg(randomPlant.getPlantImgUrl());
        this.recommendPlantName = randomPlant.getName();
        this.recommendPlantSpices = randomPlant.getSpecies();
        this.recommendPlantInfo = randomPlant.getInfo();
    }

    public void setTwoMyPlants(List<PlantRegistration> myPlants) {
        this.myPlants = myPlants.stream()
            .map(PlantRegistration::toHomeMyPlant)
            .sorted(Comparator.comparing(HomePlant::getWaterDayTo))
            .limit(5)
            .collect(Collectors.toList());
    }
}
