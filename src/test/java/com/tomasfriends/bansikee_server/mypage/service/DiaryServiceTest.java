package com.tomasfriends.bansikee_server.mypage.service;

import com.tomasfriends.bansikee_server.mypage.domain.Diary;
import com.tomasfriends.bansikee_server.mypage.domain.repository.DiaryRepository;
import com.tomasfriends.bansikee_server.mypage.service.dto.Watered;
import com.tomasfriends.bansikee_server.mypage.service.dto.Weather;
import com.tomasfriends.bansikee_server.mypage.service.dto.req.DiaryRequestDto;
import com.tomasfriends.bansikee_server.mypage.service.dto.resp.DiaryListResponseDto;
import com.tomasfriends.bansikee_server.mypage.service.dto.resp.DiaryResponseDto;
import com.tomasfriends.bansikee_server.mypage.service.exceptions.NotExistDiaryException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class DiaryServiceTest {

    @Autowired
    private DiaryService diaryService;
    @Autowired
    private DiaryRepository diaryRepository;

    private DiaryRequestDto diaryRequestDto;

    @BeforeEach
    void setUp() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("url");

        diaryRequestDto = DiaryRequestDto.builder()
            .myPlantId(3)
            .contents("일기 테스트")
            .dailyPictures(strings)
            .height(11)
            .watered(Watered.YES)
            .weather(Weather.BAD)
            .build();
    }


    @Transactional
    @DisplayName("일기 삽입 테스트")
    @Test
    @WithUserDetails(value = "1", userDetailsServiceBeanName = "customUserDetailService")
    void diarySaveTest() {
        // given
        diaryService.save(diaryRequestDto);

        // when
        List<Diary> allByMyPlantId = diaryRepository.findAllByMyPlantId(3);
        Optional<Integer> max = allByMyPlantId.stream()
            .map(Diary::getId)
            .max(Integer::compare);
        Diary byId = diaryRepository.findById(max.get()).get();

        // then
        assertThat(byId.getContents()).isEqualTo("일기 테스트");
    }

    @Transactional
    @DisplayName("모든 일기 조회 테스트")
    @Test
    @WithUserDetails(value = "1", userDetailsServiceBeanName = "customUserDetailService")
    void diaryFindAllTest() {

        List<DiaryListResponseDto> all = diaryService.findAll(3);

        Optional<Integer> max = all.stream()
            .map(DiaryListResponseDto::getDiaryId)
            .max(Integer::compareTo);

        Diary byId = diaryRepository.findById(max.get()).get();

        assertThat(byId.getUser().getId()).isEqualTo(1);
        assertThat(byId.getHeight()).isEqualTo(11);
        assertThat(byId.getContents()).isEqualTo("일기 테스트");
    }

    @Transactional
    @DisplayName("일기 조회 테스트")
    @Test
    @WithUserDetails(value = "1", userDetailsServiceBeanName = "customUserDetailService")
    void diaryFindTest() {
        // given
        diaryService.save(diaryRequestDto);

        // when
        List<Diary> allByMyPlantId = diaryRepository.findAllByMyPlantId(3);
        Integer max = allByMyPlantId.stream()
            .map(Diary::getId)
            .max(Integer::compare)
            .get();

        // then
        DiaryResponseDto diary = diaryService.findDiary(max);
        assertThat(diary.getContents()).isEqualTo("일기 테스트");
        System.out.println(diary.getDayFromBirth());
    }

    @Transactional
    @DisplayName("일기 삭제")
    @Test
    @WithUserDetails(value = "1", userDetailsServiceBeanName = "customUserDetailService")
    void diaryDeleteTest() {
        // given
        diaryService.save(diaryRequestDto);
        List<Diary> allByMyPlantId = diaryRepository.findAllByMyPlantId(3);
        Integer max = allByMyPlantId.stream()
            .map(Diary::getId)
            .max(Integer::compare)
            .get();

        // when
        diaryService.delete(max);

        // then
        assertThrows(NotExistDiaryException.class, () -> diaryService.findDiary(max));
    }
}