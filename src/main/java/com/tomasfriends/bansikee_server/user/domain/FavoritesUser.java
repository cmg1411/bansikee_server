package com.tomasfriends.bansikee_server.user.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "favorites")
public class FavoritesUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer favoritesIdx;

    private Integer plantIdx;

    private Integer userIdx;

    @ManyToOne
    @JoinColumn(name = "plantIdx",insertable = false, updatable = false)
    private PlantUser plant;
}
