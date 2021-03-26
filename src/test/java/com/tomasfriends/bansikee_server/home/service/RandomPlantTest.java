package com.tomasfriends.bansikee_server.home.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomPlantTest {

    @Test
    @DisplayName("랜덤 테스트")
    void random() {
        for (int i = 0; i < 1000; i++) {
            int count = 100;
            int randomPlantId = RandomPlant.getRandomPlantId(count);
            Assertions.assertThat(randomPlantId).isBetween(1, 100);
        }
    }
}