package com.tomasfriends.bansikee_server.onBoarding.repository;

import com.tomasfriends.bansikee_server.onBoarding.domain.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlantRepository extends JpaRepository<Plant, Integer> {

    List findBySmell(String smell);

    List findByHeight(Integer height);

    List findBySpeed(String speed);

    List findByManagementLevel(Integer level);
}
