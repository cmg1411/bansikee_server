package com.tomasfriends.bansikee_server.mypage.service;

import com.tomasfriends.bansikee_server.mypage.domain.Diary;
import com.tomasfriends.bansikee_server.mypage.domain.DiaryPicture;
import com.tomasfriends.bansikee_server.mypage.domain.MyPlant;
import com.tomasfriends.bansikee_server.mypage.domain.PlantRegistration;
import com.tomasfriends.bansikee_server.mypage.domain.repository.DiaryRepository;
import com.tomasfriends.bansikee_server.mypage.domain.repository.MyPlantRepository;
import com.tomasfriends.bansikee_server.mypage.domain.repository.PictureRepository;
import com.tomasfriends.bansikee_server.mypage.service.dto.*;
import com.tomasfriends.bansikee_server.mypage.service.dto.req.DiaryRequestDto;
import com.tomasfriends.bansikee_server.mypage.service.dto.resp.DiaryListResponseDto;
import com.tomasfriends.bansikee_server.mypage.service.dto.resp.DiaryResponseDto;
import com.tomasfriends.bansikee_server.mypage.service.exception.NotExistDiaryException;
import com.tomasfriends.bansikee_server.mypage.service.exception.NotExistMyPlantException;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiaryService implements MyPlant {

    private final DiaryRepository diaryRepository;
    private final PictureRepository pictureRepository;
    private final MyPlantRepository myPlantRepository;

    @Transactional
    public void save(DiaryRequestDto diaryRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BansikeeUser loginUser = (BansikeeUser) authentication.getPrincipal();
        Optional<PlantRegistration> registeredPlant = myPlantRepository.findById(diaryRequestDto.getMyPlantId());
        Diary diary = diaryRequestDto.toDiaryEntity(registeredPlant.orElseThrow(NotExistMyPlantException::new), loginUser);

        diaryRepository.save(diary);
        savePictures(diaryRequestDto, diary);
        checkWatered(diaryRequestDto.getWatered(), diaryRequestDto.getMyPlantId());
    }

    public void savePictures(DiaryRequestDto diaryRequestDto, Diary diary) {
        List<DiaryPicture> diaryPictures = diaryRequestDto.toDiaryPictureEntities(diary);
        diaryPictures.forEach(pictureRepository::save);
    }

    public void checkWatered(Watered watered, int myPlantId) {
        if (watered.isWatered()) {
            myPlantRepository.checkLastWaterDate(myPlantId, LocalDate.now());
        }
    }

    public List<DiaryListResponseDto> findAll(int myPlantId) {
        List<Diary> allDiary = diaryRepository.findAllByUserId(myPlantId);
        return allDiary.stream()
            .map(t -> t.toListResponseDto(t.getId(), t.getPictures().get(0), t.getCreatedDate().toLocalDate()))
            .collect(Collectors.toList());
    }

    public DiaryResponseDto findDiary(Integer diaryId) {
        Optional<Diary> foundDiary = diaryRepository.findById(diaryId);
        Diary diary = foundDiary.orElseThrow(NotExistDiaryException::new);
        System.out.println(diary.toString());
        return diary.toDiaryResponseDto();
    }

    @Transactional
    public void delete(Integer diaryId) {
        getUser(diaryId);
        diaryRepository.deleteById(diaryId);
    }

    public BansikeeUser getUser(Integer diaryId) {
        int diaryOwnerId = diaryRepository.findById(diaryId)
            .orElseThrow(NotExistMyPlantException::new)
            .getUser()
            .getId();
        return checkAuth(diaryOwnerId);
    }
}
