package com.tomasfriends.bansikee_server.dictionary.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class FavoritesDictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer favoritesIdx;

    private Integer plantIdx;

    private Integer userIdx;

}
