package com.tomasfriends.bansikee_server.user.repository;

import com.tomasfriends.bansikee_server.user.domain.FavoritesUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritesUserRepository extends JpaRepository<FavoritesUser, Integer> {

    List<FavoritesUser> findByUserIdx(Integer userIdx);
}
