package com.tomasfriends.bansikee_server.mypage.domain.repository;

import com.tomasfriends.bansikee_server.mypage.domain.Diary;
import com.tomasfriends.bansikee_server.mypage.domain.PlantRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Integer> {
    List<Diary> findAllByUserId(int diaryIdx);
}
