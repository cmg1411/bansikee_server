package com.tomasfriends.bansikee_server.mypage.service;

import com.tomasfriends.bansikee_server.common.AuthenticationUser;
import com.tomasfriends.bansikee_server.mypage.domain.MyPlant;
import com.tomasfriends.bansikee_server.mypage.domain.PlantRegistration;
import com.tomasfriends.bansikee_server.mypage.domain.repository.MyPlantRepository;
import com.tomasfriends.bansikee_server.mypage.service.dto.resp.MyPlantListResponseDto;
import com.tomasfriends.bansikee_server.mypage.service.dto.req.MyPlantPatchRequestDto;
import com.tomasfriends.bansikee_server.mypage.service.dto.resp.MyPlantResponseDto;
import com.tomasfriends.bansikee_server.mypage.service.dto.req.PlantRegistrationRequestDto;
import com.tomasfriends.bansikee_server.mypage.service.exceptions.NotExistMyPlantException;
import com.tomasfriends.bansikee_server.onBoarding.domain.Plant;
import com.tomasfriends.bansikee_server.onBoarding.repository.PlantRepository;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import com.tomasfriends.bansikee_server.sign.service.exceptions.NotRegisteredUserIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlantRegisterService implements MyPlant {

    private final MyPlantRepository myPlantRepository;
    private final PlantRepository plantRepository;

    @Transactional
    public void save(PlantRegistrationRequestDto plantRegistrationRequestDto) {
        BansikeeUser loginUser = AuthenticationUser.getAuthenticationUser();
        Optional<Plant> plant = plantRepository.findById(plantRegistrationRequestDto.getPlantId());
        PlantRegistration myPlant = plantRegistrationRequestDto.toEntity(loginUser, plant.orElseThrow(NotRegisteredUserIdException::new));
        myPlantRepository.save(myPlant);
    }

    public List<MyPlantListResponseDto> findAll() {
        BansikeeUser loginUser = AuthenticationUser.getAuthenticationUser();
        List<PlantRegistration> allMyPlantRegistered = myPlantRepository.findAllByUserId(loginUser.getId());
        return allMyPlantRegistered.stream()
            .map(PlantRegistration::toListResponseDto)
            .collect(Collectors.toList());
    }

    public MyPlantResponseDto findPlant(Integer myPlantId) {
        Optional<PlantRegistration> foundMyPlant = myPlantRepository.findById(myPlantId);
        PlantRegistration plantRegistration = foundMyPlant.orElseThrow(NotExistMyPlantException::new);
        return plantRegistration.toMyPlantResponseDto();
    }

    @Transactional
    public void patch(MyPlantPatchRequestDto myPlantPatchRequestDto) {
        BansikeeUser loginUser = getUser(myPlantPatchRequestDto.getMyPlantId());
        Optional<Plant> plant = plantRepository.findById(myPlantPatchRequestDto.getPlantId());
        PlantRegistration patchedPlant = myPlantPatchRequestDto.toPatchEntity(loginUser, plant.orElseThrow(NotRegisteredUserIdException::new));
        myPlantRepository.save(patchedPlant);
    }

    @Transactional
    public void delete(Integer myPlantId) {
        getUser(myPlantId);
        myPlantRepository.deleteById(myPlantId);
    }

    public BansikeeUser getUser(Integer myPlantId) {
        int myPlantOwnerId = myPlantRepository.findById(myPlantId)
            .orElseThrow(NotExistMyPlantException::new)
            .getUser()
            .getId();
        return checkAuth(myPlantOwnerId);
    }
}