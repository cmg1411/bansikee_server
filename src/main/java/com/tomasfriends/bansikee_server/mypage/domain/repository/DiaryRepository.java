package com.tomasfriends.bansikee_server.mypage.domain.repository;

import com.tomasfriends.bansikee_server.mypage.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Integer> {
    List<Diary> findAllByUserId(int diaryIdx);
}
