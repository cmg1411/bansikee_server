package com.tomasfriends.bansikee_server.home.service;

import java.security.SecureRandom;

public class RandomPlant {

    private RandomPlant() {
    }

    public static int getRandomPlantId(long count) {
        SecureRandom random = new SecureRandom();
        return random.nextInt((int)count) + 1;
    }
}
