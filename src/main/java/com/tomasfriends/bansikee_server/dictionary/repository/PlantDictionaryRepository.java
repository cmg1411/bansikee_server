package com.tomasfriends.bansikee_server.dictionary.repository;

import com.tomasfriends.bansikee_server.dictionary.domain.PlantDictionary;
import com.tomasfriends.bansikee_server.dictionary.domain.SearchHistory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlantDictionaryRepository extends JpaRepository<PlantDictionary,Integer>{

   @Query(value = "SELECT p FROM PlantDictionary p LEFT JOIN p.favorites f ON p.plantIdx = f.plantIdx where p.name like CONCAT('%',:keyWord,'%') or p.species like CONCAT('%',:keyWord,'%') GROUP BY p.plantIdx ORDER BY COUNT(f.plantIdx) DESC")
   List<PlantDictionary> findByFavorites(@Param("keyWord") String keyWord, Pageable pageable);

    List<PlantDictionary> findByNameOrSpeciesContaining(String keyWord, String speciesKeyWord, Pageable pageable);

}
