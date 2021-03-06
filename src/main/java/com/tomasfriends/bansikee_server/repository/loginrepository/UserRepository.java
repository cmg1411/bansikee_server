package com.tomasfriends.bansikee_server.repository.loginrepository;

import com.tomasfriends.bansikee_server.dto.servicedto.BansikeeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<BansikeeUser, Integer> {

    Optional<BansikeeUser> findByEmail(String email);
    Optional<BansikeeUser> findTopByEmail(String email);
}
