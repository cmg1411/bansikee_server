package com.tomasfriends.bansikee_server.mypage.domain.repository;

import com.tomasfriends.bansikee_server.mypage.domain.DiaryPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<DiaryPicture, Integer> {
}
