package com.tomasfriends.bansikee_server.dictionary.repository;

import com.tomasfriends.bansikee_server.dictionary.domain.FavoritesDictionary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritesDictionaryRepository extends JpaRepository<FavoritesDictionary, Integer> {

    boolean existsByPlantIdxAndUserIdx(Integer plantIdx, Integer UserIdx);
}
