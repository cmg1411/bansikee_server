package com.tomasfriends.bansikee_server.onBoarding.repository;

import com.tomasfriends.bansikee_server.onBoarding.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OnBoardUserRepository extends JpaRepository<User, Integer> {
    List findByUseridx (Integer userIdx);
}
