package com.tomasfriends.bansikee_server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userIdx")
    private int id;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(unique = true, length = 50)
    private String password;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "userImageUrl", columnDefinition = "TEXT", length = 200)
    private String profileImageURL;
}
