package com.tomasfriends.bansikee_server.mypage.domain;

import com.tomasfriends.bansikee_server.home.service.dto.HomePlant;
import com.tomasfriends.bansikee_server.mypage.service.dto.resp.MyPlantListResponseDto;
import com.tomasfriends.bansikee_server.mypage.service.dto.resp.MyPlantResponseDto;
import com.tomasfriends.bansikee_server.onBoarding.domain.Plant;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Transactional
class PlantRegistrationTest {

    @MockBean private BansikeeUser bansikeeUser;
    @MockBean private List<Diary> diaries;
    private Plant plant;


    @BeforeEach
    void setUp() {
        plant = new Plant();
        plant.setPlantIdx(11);
    }

    @Test
    void calculateDate() {
        // given
        LocalDateTime birth = LocalDateTime.of(2021, 2, 20, 14, 22);
        LocalDateTime writeDate = LocalDateTime.of(2021, 2, 22, 14, 22);

        // when
        long dDay = PlantRegistration.getDDay(birth, writeDate);

        // then
//        Assertions.assertThat(dDay).isEqualTo(27);
        assertThat(dDay).isEqualTo(2);
    }

    @Test
    void calculateWaterDay() {
        // given
        LocalDateTime now = LocalDateTime.now();
        LocalDate wateredDay = now.toLocalDate().minusDays(7);
        PlantRegistration p
            = new PlantRegistration(1, "a", "A", now, "a", 3, LocalDate.now(), wateredDay, bansikeeUser, plant, diaries);

        // when
        Map<String, Long> waterDays = p.getWaterDaysNotice();

        System.out.println(waterDays.get("from"));
        System.out.println(waterDays.get("to"));

        // then
        assertThat(waterDays).containsEntry("from", 7L).containsEntry("to", -4L);;
    }

    @Test
    void 한번도_물_준적_없으면_음수1_반환() {
        // given
        LocalDateTime now = LocalDateTime.now();
        LocalDate wateredDay = null;
        PlantRegistration p
            = new PlantRegistration(1, "a", "A", now, "a", 7, LocalDate.now(), wateredDay, bansikeeUser, plant, diaries);

        // when
        Map<String, Long> waterDays = p.getWaterDaysNotice();

        // then
        assertThat(waterDays).containsEntry("from", -1L).containsEntry("to", -1L);
    }

    @Test
    void 오늘_일기를_쓰지_않았다면_todayDiaryStatus_가_False() {
        // given
        LocalDateTime now = LocalDateTime.now();
        PlantRegistration p
            = new PlantRegistration(1, "a", "A", now, "a", 7, null, now.toLocalDate(), bansikeeUser, plant, diaries);

        // when
        HomePlant homePlant = p.toHomeMyPlant();

        // then
        assertThat(homePlant.isTodayDiaryStatus()).isFalse();
    }

    @Test
    void 응답_DTO_변환() {
        // given
        LocalDateTime now = LocalDateTime.now();
        PlantRegistration p
            = new PlantRegistration(1, "a", "A", now, "a", 7, null, now.toLocalDate(), bansikeeUser, plant, diaries);

        // when
        MyPlantListResponseDto myPlantListResponseDto = p.toListResponseDto();
        MyPlantResponseDto myPlantResponseDto = p.toMyPlantResponseDto();

        // then
        assertThat(myPlantListResponseDto).isInstanceOf(MyPlantListResponseDto.class).isNotNull();
        assertThat(myPlantResponseDto).isInstanceOf(MyPlantResponseDto.class).isNotNull();
    }
}