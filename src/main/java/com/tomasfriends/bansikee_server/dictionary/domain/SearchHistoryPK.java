package com.tomasfriends.bansikee_server.dictionary.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class SearchHistoryPK implements Serializable {
    private int userIdx;
    private int plantIdx;
}
