package com.tomasfriends.bansikee_server.mypage.service;

import com.tomasfriends.bansikee_server.mypage.domain.PlantRegistration;
import com.tomasfriends.bansikee_server.mypage.domain.repository.MyPlantRepository;
import com.tomasfriends.bansikee_server.mypage.service.dto.PlantRegistrationRequestDto;
import com.tomasfriends.bansikee_server.onBoarding.domain.Plant;
import com.tomasfriends.bansikee_server.onBoarding.repository.PlantRepository;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import com.tomasfriends.bansikee_server.sign.service.exceptions.NotRegisteredUserIdException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PlantRegisterService {

    private final MyPlantRepository myPlantRepository;
    private final PlantRepository plantRepository;

    public PlantRegisterService(MyPlantRepository myPlantRepository, PlantRepository plantRepository) {
        this.myPlantRepository = myPlantRepository;
        this.plantRepository = plantRepository;
    }

    @Transactional
    public void save(PlantRegistrationRequestDto plantRegistrationRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BansikeeUser loginUser = (BansikeeUser) authentication.getPrincipal();
        Optional<Plant> plant = plantRepository.findById(plantRegistrationRequestDto.getPlantId());
        PlantRegistration myPlant = plantRegistrationRequestDto.toEntity(loginUser, plant.orElseThrow(NotRegisteredUserIdException::new));
        myPlantRepository.save(myPlant);
    }
}
