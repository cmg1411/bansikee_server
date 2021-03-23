package com.tomasfriends.bansikee_server.mypage.domain.repository;

import com.tomasfriends.bansikee_server.mypage.domain.PlantRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyPlantRepository extends JpaRepository<PlantRegistration, Integer> {
    List<PlantRegistration> findAllByUserId(int plantIdx);
}
