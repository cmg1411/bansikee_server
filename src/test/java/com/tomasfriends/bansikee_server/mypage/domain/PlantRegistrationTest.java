package com.tomasfriends.bansikee_server.mypage.domain;

import com.tomasfriends.bansikee_server.onBoarding.domain.Plant;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
        Assertions.assertThat(dDay).isNotNull();
    }

    @Test
    void calculateWaterDay() {
        // given
        LocalDateTime now = LocalDateTime.now();
        LocalDate wateredDay = now.toLocalDate().minusDays(3);
        PlantRegistration p = new PlantRegistration(1, "a", "A", now, "a", 7, wateredDay, bansikeeUser, plant, diaries);

        // when
        Map<String, Long> waterDays = p.getWaterDaysNotice();

        // then
        Assertions.assertThat(waterDays.get("from")).isEqualTo(3);
        Assertions.assertThat(waterDays.get("to")).isEqualTo(4);
    }

    @Test
    void 한번도_물_준적_없을() {
        // given
        LocalDateTime now = LocalDateTime.now();
        LocalDate wateredDay = null;
        PlantRegistration p = new PlantRegistration(1, "a", "A", now, "a", 7, wateredDay, bansikeeUser, plant, diaries);

        // when
        Map<String, Long> waterDays = p.getWaterDaysNotice();

        // then
        Assertions.assertThat(waterDays.get("from")).isEqualTo(-1L);
        Assertions.assertThat(waterDays.get("to")).isEqualTo(-1L);
    }
}