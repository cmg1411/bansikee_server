package com.tomasfriends.bansikee_server.home.service;

import java.util.concurrent.ThreadLocalRandom;

public class RandomPlant {

    private RandomPlant() {
    }

    public static int getRandomPlantId(long count) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextInt((int)count) + 1;
    }
}
