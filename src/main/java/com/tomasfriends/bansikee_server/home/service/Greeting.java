package com.tomasfriends.bansikee_server.home.service;

import lombok.Getter;

import java.util.Arrays;
import java.util.Calendar;

@Getter
public enum Greeting {

    SUN(1, "반식이와 기분좋은 일요일을 시작하세요 !"),
    MON(2, "즐거운 월요일 되세요 !"),
    THU(3, "즐거운 화요일 되세요 !"),
    WEN(4, "즐거운 수요일 되세요 !"),
    TUR(5, "즐거운 목요일 되세요 !"),
    FRI(6, "즐거운 금요일 되세요 !"),
    SAT(7, "즐거운 토요일 되세요 !");

    private final int dayOfWeek;
    private final String greet;

    Greeting(int dayOfWeek, String greet) {
        this.dayOfWeek = dayOfWeek;
        this.greet = greet;
    }

    public static String getDayGreet() {
        Calendar calendar = Calendar.getInstance();
        return Arrays.stream(values())
            .filter(day -> day.getDayOfWeek() == calendar.get(Calendar.DAY_OF_WEEK))
            .map(Greeting::getGreet)
            .findAny()
            .orElseGet(MON::getGreet);
    }
}
