package com.tomasfriends.bansikee_server.home.service;

import com.tomasfriends.bansikee_server.common.AuthenticationUser;
import com.tomasfriends.bansikee_server.home.service.dto.HomeResponseDto;
import com.tomasfriends.bansikee_server.home.service.exception.NotExistPlantException;
import com.tomasfriends.bansikee_server.mypage.domain.PlantRegistration;
import com.tomasfriends.bansikee_server.mypage.domain.repository.DiaryRepository;
import com.tomasfriends.bansikee_server.mypage.domain.repository.MyPlantRepository;
import com.tomasfriends.bansikee_server.onBoarding.domain.Plant;
import com.tomasfriends.bansikee_server.onBoarding.repository.PlantRepository;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import com.tomasfriends.bansikee_server.sign.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.tomasfriends.bansikee_server.home.service.RandomPlant.getRandomPlantId;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final PlantRepository plantRepository;
    private final MyPlantRepository myPlantRepository;
    private final DiaryRepository diaryRepository;

    public HomeResponseDto getHome() {
        HomeResponseDto responseDto = new HomeResponseDto();

        BansikeeUser user = setUserInfo(responseDto);
        responseDto.setGreeting(Greeting.getDayGreet());
        responseDto.setRandomRecommendPlant(getRandomPlant());
        setMyPlantInfo(responseDto, user);

        return responseDto;
    }

    private BansikeeUser setUserInfo(HomeResponseDto responseDto) {
        BansikeeUser user = AuthenticationUser.getAuthenticationUser();
        responseDto.setUser(user);
        return user;
    }

    protected Plant getRandomPlant() {
        int randomPlantId = getRandomPlantId(plantRepository.count());
        return plantRepository.findById(randomPlantId).orElseThrow(NotExistPlantException::new);
    }

    private void setMyPlantInfo(HomeResponseDto responseDto, BansikeeUser user) {
        List<PlantRegistration> myPlants = myPlantRepository.findAllByUserId(user.getId());
        responseDto.setTwoMyPlants(myPlants);
    }
}
