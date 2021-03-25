package com.tomasfriends.bansikee_server.home.service.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class HomePlant {

    private int myPlantId;
    private String myPlantNickName;
    private String myPlantAge;
    private String myPlantSpecies;
    private int myPlantWaterCycle;
    private LocalDate myPlantLastWaterDay;
    private long waterDayFrom;
    private long waterDayTo;
    private boolean todayDiaryStatus;

    @Builder
    public HomePlant(int myPlantId, String myPlantNickName, String myPlantAge, String myPlantSpecies, int myPlantWaterCycle,
                     LocalDate myPlantLastWaterDay, long waterDayFrom, long waterDayTo, boolean todayDiaryStatus) {
        this.myPlantId = myPlantId;
        this.myPlantNickName = myPlantNickName;
        this.myPlantAge = myPlantAge;
        this.myPlantSpecies = myPlantSpecies;
        this.myPlantWaterCycle = myPlantWaterCycle;
        this.myPlantLastWaterDay = myPlantLastWaterDay;
        this.waterDayFrom = waterDayFrom;
        this.waterDayTo = waterDayTo;
        this.todayDiaryStatus = todayDiaryStatus;
    }
}
