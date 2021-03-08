package com.tomasfriends.bansikee_server.onBoarding.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer plantIdx;

    private String name;

    private String species;

    private String info;

    private Integer managementLevel;

    private Integer height;

    private Integer width;

    private Integer speed;

    private Integer smell;

    private String temperature;

    private String water;

    private String light;

    private String area;

    private String plantImgUrl;

}
