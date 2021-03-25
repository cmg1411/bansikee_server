package com.tomasfriends.bansikee_server.home.entity;

import com.tomasfriends.bansikee_server.home.service.Greeting;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GreetingTest {

    @Test
    void dayTest() {
        String dayGreet = Greeting.getDayGreet();
//        System.out.println(dayGreet);

        Assertions.assertThat(dayGreet).isNotNull();
    }
}