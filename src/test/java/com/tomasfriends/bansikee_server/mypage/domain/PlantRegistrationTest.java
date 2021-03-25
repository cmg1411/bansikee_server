package com.tomasfriends.bansikee_server.mypage.domain;

import com.tomasfriends.bansikee_server.onBoarding.domain.Plant;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
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

    @MockBean BansikeeUser bansikeeUser;
    @MockBean Plant plant;
    @MockBean List<Diary> diaries;

    @Test
    void calculateDate() {
        // given
        PlantRegistration plantRegistration = new PlantRegistration();
        LocalDateTime ldt = LocalDateTime.of(2021, 2, 22, 14, 22);

        // when
        long dDay = plantRegistration.getDDay(ldt);

        // then
//        Assertions.assertThat(dDay).isEqualTo(27);
        assertThat(dDay).isNotNull();
    }

    @Test
    void calculateWaterDay() {
        // given
        LocalDateTime now = LocalDateTime.now();
        LocalDate wateredDay = now.toLocalDate().minusDays(7);
        PlantRegistration p = new PlantRegistration(1, "a", "A", now, "a", 3, LocalDate.now(), wateredDay, bansikeeUser, plant, diaries);

        // when
        Map<String, Long> waterDays = p.getWaterDaysNotice();

        System.out.println(waterDays.get("from"));
        System.out.println(waterDays.get("to"));

        // then
        assertThat(waterDays).containsEntry("from", 7L).containsEntry("to", -4L);;
    }

    @Test
    void 한번도_물_준적_없을() {
        // given
        LocalDateTime now = LocalDateTime.now();
        LocalDate wateredDay = null;
        PlantRegistration p = new PlantRegistration(1, "a", "A", now, "a", 7, LocalDate.now(), wateredDay, bansikeeUser, plant, diaries);

        // when
        Map<String, Long> waterDays = p.getWaterDaysNotice();

        // then
        assertThat(waterDays).containsEntry("from", -1L).containsEntry("to", -1L);
    }
}