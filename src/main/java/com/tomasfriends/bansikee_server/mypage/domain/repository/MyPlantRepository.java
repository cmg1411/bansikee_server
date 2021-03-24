package com.tomasfriends.bansikee_server.mypage.domain.repository;

import com.tomasfriends.bansikee_server.mypage.domain.PlantRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MyPlantRepository extends JpaRepository<PlantRegistration, Integer> {
    List<PlantRegistration> findAllByUserId(int plantIdx);

    @Modifying
    @Query("UPDATE PlantRegistration SET lastWateredDate = :now WHERE id = :myPlantId")
    void checkLastWaterDate(@Param("myPlantId") int myPlantId,
                            @Param("now") LocalDate now);
}
