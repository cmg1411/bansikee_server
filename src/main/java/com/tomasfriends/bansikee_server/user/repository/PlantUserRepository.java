package com.tomasfriends.bansikee_server.user.repository;

import com.tomasfriends.bansikee_server.user.domain.PlantUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantUserRepository extends JpaRepository<PlantUser, Integer> {
}
