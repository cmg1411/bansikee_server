package com.tomasfriends.bansikee_server.mypage.service;

import com.tomasfriends.bansikee_server.mypage.domain.PlantRegistration;
import com.tomasfriends.bansikee_server.mypage.domain.repository.MyPlantRepository;
import com.tomasfriends.bansikee_server.mypage.service.dto.req.MyPlantPatchRequestDto;
import com.tomasfriends.bansikee_server.mypage.service.dto.req.PlantRegistrationRequestDto;
import com.tomasfriends.bansikee_server.mypage.service.dto.resp.MyPlantListResponseDto;
import com.tomasfriends.bansikee_server.mypage.service.dto.resp.MyPlantResponseDto;
import com.tomasfriends.bansikee_server.mypage.service.exceptions.NotExistMyPlantException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PlantRegisterServiceTest {


    @Autowired private PlantRegisterService plantRegisterService;
    @Autowired private MyPlantRepository myPlantRepository;

    private PlantRegistrationRequestDto dto;

    @BeforeEach
    void setUpDto() {
        dto = PlantRegistrationRequestDto.builder()
            .pictureUrl("url")
            .waterPeriod(3)
            .plantNickName("testNic")
            .plantIntro("test")
            .plantId(2)
            .plantBirth(LocalDateTime.now())
            .build();
    }

    @Transactional
    @WithUserDetails(value = "2", userDetailsServiceBeanName = "customUserDetailService")
    @DisplayName("PlantRegistrationRequestDto 를 받아 내 식물로 저장한다.")
    @Test
    void saveMyPlantTest() {
        // when
        plantRegisterService.save(dto);

        List<PlantRegistration> allByUserId = myPlantRepository.findAllByUserId(2);
        PlantRegistration plantRegistrationInTest = allByUserId.stream()
            .filter(myPlant -> myPlant.getPlantNickName().equals("testNic"))
            .findFirst()
            .get();

        // then
        assertThat(plantRegistrationInTest.getPictureUrl()).isEqualTo("url");
        assertThat(plantRegistrationInTest.getPlantIntroduce()).isEqualTo("test");
    }

    @Transactional
    @WithUserDetails(value = "2", userDetailsServiceBeanName = "customUserDetailService")
    @DisplayName("등록된 나의 식물 리스트를 가져온다.")
    @Test
    void myAllPlantSelectTest() {
        // given
        plantRegisterService.save(dto);

        // when
        List<MyPlantListResponseDto> allMyPlant = plantRegisterService.findAll();

        // then
        for (MyPlantListResponseDto myPlantListResponseDto : allMyPlant) {
            Integer myPlantId = myPlantListResponseDto.getMyPlantId();
            Optional<PlantRegistration> byId = myPlantRepository.findById(myPlantId);
            int myPlantUserId = byId.get().getUser().getId();
            assertThat(myPlantUserId).isEqualTo(2);
            assertThat(myPlantUserId).isNotEqualTo(1);
        }
    }

    @Transactional
    @WithUserDetails(value = "2", userDetailsServiceBeanName = "customUserDetailService")
    @DisplayName("myPlant 인덱스로 데이터를 조회한다.")
    @Test
    void findByMyPlantIdTest() {
        // given
        plantRegisterService.save(dto);
        List<PlantRegistration> allPlant = myPlantRepository.findAllByUserId(2);
        Integer myPlantId = getLastMyPlantId(allPlant);

        // when
        MyPlantResponseDto plant = plantRegisterService.findPlant(myPlantId);

        // then
        assertThat(plant.getWater()).isEqualTo(3);
        assertThat(plant.getNickName()).isEqualTo("testNic");
        assertThat(plant.getContents()).isEqualTo("test");
    }

    @Transactional
    @WithUserDetails(value = "2", userDetailsServiceBeanName = "customUserDetailService")
    @DisplayName("내 식물의 정보를 수정할 수 있다.")
    @Test
    void patchMyPlantTest() {
        // given
        plantRegisterService.save(dto);
        List<PlantRegistration> allPlant = myPlantRepository.findAllByUserId(2);
        Integer myPlantId = getLastMyPlantId(allPlant);
        MyPlantPatchRequestDto patchDto
            = new MyPlantPatchRequestDto(myPlantId, "url2", LocalDateTime.now(), "patchedInfo", 2, "patchedNic", 3);

        // when
        plantRegisterService.patch(patchDto);

        // then
        PlantRegistration patchedPlant = myPlantRepository.findById(myPlantId).get();
        assertThat(patchedPlant.getPictureUrl()).isEqualTo("url2");
        assertThat(patchedPlant.getPlantIntroduce()).isEqualTo("patchedInfo");
        assertThat(patchedPlant.getPlantNickName()).isEqualTo("patchedNic");
    }

    @Transactional
    @DisplayName("삭제한 내식물을 조회하려하면 NotExistMyPlantException 예외가 발생한다.")
    @Test
    @WithUserDetails(value = "1", userDetailsServiceBeanName = "customUserDetailService")
    void myPlantDeleteTest() {
        // given
        plantRegisterService.save(dto);
        List<PlantRegistration> allPlant = myPlantRepository.findAllByUserId(1);
        Integer myPlantId = getLastMyPlantId(allPlant);

        // when
        plantRegisterService.delete(myPlantId);

        // then
        assertThrows(NotExistMyPlantException.class, () -> plantRegisterService.findPlant(myPlantId));
    }


    private Integer getLastMyPlantId(List<PlantRegistration> allPlant) {
        Integer max = allPlant.stream()
            .map(PlantRegistration::getId)
            .max(Integer::compare)
            .get();
        return max;
    }
}