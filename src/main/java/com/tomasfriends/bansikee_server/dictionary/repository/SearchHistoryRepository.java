package com.tomasfriends.bansikee_server.dictionary.repository;

import com.tomasfriends.bansikee_server.dictionary.domain.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Integer> {

    List<SearchHistory> findByUserIdxOrderByUpdatedAtDesc(Integer userIdx);

    @Modifying
//    @Query(value = "delete from search_history where updated_at < DATE_SUB(updated_at,INTERVAL 3 day )", nativeQuery=true)
    void deleteByUpdatedAtIsBefore(LocalDateTime date);
}
