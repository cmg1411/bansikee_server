package com.tomasfriends.bansikee_server.mypage.service;

import com.tomasfriends.bansikee_server.common.AuthenticationUser;
import com.tomasfriends.bansikee_server.mypage.domain.Diary;
import com.tomasfriends.bansikee_server.mypage.domain.DiaryPicture;
import com.tomasfriends.bansikee_server.mypage.domain.PlantRegistration;
import com.tomasfriends.bansikee_server.mypage.domain.repository.DiaryRepository;
import com.tomasfriends.bansikee_server.mypage.domain.repository.MyPlantRepository;
import com.tomasfriends.bansikee_server.mypage.domain.repository.PictureRepository;
import com.tomasfriends.bansikee_server.mypage.service.dto.DiaryListResponseDto;
import com.tomasfriends.bansikee_server.mypage.service.dto.DiaryRequestDto;
import com.tomasfriends.bansikee_server.mypage.service.exception.NotExistMyPlantException;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final PictureRepository pictureRepository;
    private final MyPlantRepository myPlantRepository;

    public void save(DiaryRequestDto diaryRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BansikeeUser loginUser = (BansikeeUser) authentication.getPrincipal();
        Optional<PlantRegistration> registeredPlant = myPlantRepository.findById(diaryRequestDto.getMyPlantId());
        Diary diary = diaryRequestDto.toDiaryEntity(registeredPlant.orElseThrow(NotExistMyPlantException::new), loginUser);
        diaryRepository.save(diary);
        savePictures(diaryRequestDto, diary);
    }

    public void savePictures(DiaryRequestDto diaryRequestDto, Diary diary) {
        List<DiaryPicture> diaryPictures = diaryRequestDto.toDiaryPictureEntities(diary);
        diaryPictures.forEach(pictureRepository::save);
    }

    public List<DiaryListResponseDto> findAll(int myPlantId) {
        List<Diary> allDiary = diaryRepository.findAllByUserId(myPlantId);
        return allDiary.stream()
            .map(t -> t.toListResponseDto(t.getPictures().get(0), t.getCreatedDate().toLocalDate()))
            .collect(Collectors.toList());
    }

//    public MyPlantResponseDto findPlant(Integer myPlantId) {
//        Optional<PlantRegistration> foundMyPlant = myPlantRepository.findById(myPlantId);
//        PlantRegistration plantRegistration = foundMyPlant.orElseThrow(NotExistMyPlantException::new);
//        return plantRegistration.toMyPlantResponseDto();
//    }
//
//    @Transactional
//    public void patch(MyPlantPatchRequestDto myPlantPatchRequestDto) {
//        BansikeeUser loginUser = getUser(myPlantPatchRequestDto.getMyPlantId());
//        Optional<Plant> plant = plantRepository.findById(myPlantPatchRequestDto.getPlantId());
//        PlantRegistration patchedPlant = myPlantPatchRequestDto.toPatchEntity(loginUser, plant.orElseThrow(NotRegisteredUserIdException::new));
//        myPlantRepository.save(patchedPlant);
//    }
//
//    @Transactional
//    public void delete(Integer myPlantId) {
//        getUser(myPlantId);
//        myPlantRepository.deleteById(myPlantId);
//    }
//
//    public BansikeeUser getUser(Integer myPlantId) {
//        int myPlantOwnerId = myPlantRepository.findById(myPlantId)
//            .orElseThrow(NotExistMyPlantException::new)
//            .getUser()
//            .getId();
//        return checkAuth(myPlantOwnerId);
//    }
}
