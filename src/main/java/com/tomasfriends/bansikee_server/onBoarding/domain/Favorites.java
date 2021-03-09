package com.tomasfriends.bansikee_server.onBoarding.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Favorites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer favoritesIdx;

    private Integer plantIdx;

    private Integer userIdx;

    public static Favorites from(Integer plantIdx, Integer userIdx){
        Favorites favorites = new Favorites();

        favorites.plantIdx = plantIdx;
        favorites.userIdx = userIdx;

        return favorites;
    }
}
