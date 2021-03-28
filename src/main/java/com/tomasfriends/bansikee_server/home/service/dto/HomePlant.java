package com.tomasfriends.bansikee_server.home.service.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class HomePlant {

    private final int myPlantId;
    private final String myPlantNickName;
    private final String myPlantImgUrl;
    private final String myPlantAge;
    private final String myPlantName;
    private final int myPlantWaterCycle;
    private final LocalDate myPlantLastWaterDay;
    private final long waterDayFrom;
    private final long waterDayTo;
    private final boolean todayDiaryStatus;

    @Builder
    public HomePlant(int myPlantId, String myPlantNickName, String myPlantImgUrl, String myPlantAge, String myPlantName, int myPlantWaterCycle,
                     LocalDate myPlantLastWaterDay, long waterDayFrom, long waterDayTo, boolean todayDiaryStatus) {
        this.myPlantId = myPlantId;
        this.myPlantNickName = myPlantNickName;
        this.myPlantImgUrl = myPlantImgUrl;
        this.myPlantAge = myPlantAge;
        this.myPlantName = myPlantName;
        this.myPlantWaterCycle = myPlantWaterCycle;
        this.myPlantLastWaterDay = myPlantLastWaterDay;
        this.waterDayFrom = waterDayFrom;
        this.waterDayTo = waterDayTo;
        this.todayDiaryStatus = todayDiaryStatus;
    }
}
