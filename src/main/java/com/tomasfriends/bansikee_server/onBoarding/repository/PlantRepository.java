package com.tomasfriends.bansikee_server.onBoarding.repository;

import com.tomasfriends.bansikee_server.onBoarding.domain.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant, Integer> {
}
