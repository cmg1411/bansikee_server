package com.tomasfriends.bansikee_server.onBoarding.repository;

import com.tomasfriends.bansikee_server.onBoarding.domain.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritesRepository extends JpaRepository<Favorites, Integer> {

    boolean existsByPlantIdxAndUserIdx(Integer plantIdx, Integer UserIdx);

    Favorites findByPlantIdxAndUserIdx(Integer plantIdx, Integer UserIdx);
}
