package com.tomasfriends.bansikee_server.mypage.service.dto;

import lombok.Getter;

@Getter
public enum Watered {
    YES(true), NO(false);

    private boolean watered;

    Watered(boolean watered) {
        this.watered = watered;
    }
}
