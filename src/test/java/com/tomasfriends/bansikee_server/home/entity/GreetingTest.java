package com.tomasfriends.bansikee_server.home.entity;

import com.tomasfriends.bansikee_server.home.service.Greeting;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.Calendar;
import java.util.Optional;

class GreetingTest {

    @DisplayName("요일에 맞는 인사를 출력한다.")
    @Test
    void greetingTest() {
        String dayGreet = Greeting.getDayGreet();

        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        Optional<Greeting> any = Arrays.stream(Greeting.values())
            .filter(day -> day.getDayOfWeek() == dayOfWeek)
            .findAny();

        Assertions.assertThat(any).isNotNull();
        Assertions.assertThat(dayGreet).isEqualTo(any.get().getGreet());
    }
}