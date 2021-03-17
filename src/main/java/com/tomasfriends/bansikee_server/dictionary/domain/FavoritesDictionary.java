package com.tomasfriends.bansikee_server.dictionary.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "favorites")
public class FavoritesDictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer favoritesIdx;

    private Integer plantIdx;

    private Integer userIdx;

}
