package com.tomasfriends.bansikee_server.onBoarding.service;

import com.tomasfriends.bansikee_server.onBoarding.domain.Favorites;
import com.tomasfriends.bansikee_server.onBoarding.domain.OnBoardingAnswer;
import com.tomasfriends.bansikee_server.onBoarding.domain.Plant;
import com.tomasfriends.bansikee_server.onBoarding.dto.*;
import com.tomasfriends.bansikee_server.onBoarding.repository.OnBoardingAnswerRepository;
import com.tomasfriends.bansikee_server.onBoarding.repository.FavoritesRepository;
import com.tomasfriends.bansikee_server.onBoarding.repository.PlantRepository;
import com.tomasfriends.bansikee_server.onBoarding.repository.QuestionRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;


@Transactional
public class OnBoardingService {

    private QuestionRepository questionRepository;
    private OnBoardingAnswerRepository onBoardingAnswerRepository;
    private FavoritesRepository favoritesRepository;
    private PlantRepository plantRepository;

    public OnBoardingService(QuestionRepository questionRepository, OnBoardingAnswerRepository onBoardingAnswerRepository, FavoritesRepository favoritesRepository, PlantRepository plantRepository) {
        this.questionRepository = questionRepository;
        this.onBoardingAnswerRepository = onBoardingAnswerRepository;
        this.favoritesRepository = favoritesRepository;
        this.plantRepository = plantRepository;
    }


    /**
     * 전체 질문 조회
     */
    public List<ResQuestionDto> getQuestions(Integer userIdx) {

        //userIdx로 onBoarding 조회
        //if 0
        //make 1


        List<ResQuestionDto> resQuestionDtos = questionRepository.findAll()
                .stream()
                .map(ResQuestionDto::of)
                .collect(Collectors.toList());

        return resQuestionDtos;
    }

    /**
     * 결과 DB에 저장
     */
    public void postAnswer(Integer userIdx, ReqAnswerListDto reqAnswerListDto) {

        for (ReqAnswerDto str : reqAnswerListDto.getReqAnswerList()) {

            OnBoardingAnswer onBoardingAnswer = OnBoardingAnswer.from(userIdx, str.getQuestionIdx(), str.getOptionIdx());

            onBoardingAnswerRepository.save(onBoardingAnswer);
        }

    }

    /**
     * 추천 식물 리스트 가져오기
     */
    public List<ResRecoPlantDto> getRecommendPlants(Integer userIdx) {

        List<OnBoardingAnswer> onBoardingAnswer = onBoardingAnswerRepository.findByUserIdx(userIdx);
        List<Plant> plantList = plantRepository.findAll();

        ArrayList<Plant> four = new ArrayList<>();
        ArrayList<Plant> three = new ArrayList<>();
        ArrayList<Plant> two = new ArrayList<>();
        int[] countList = new int[plantList.size()];

        ArrayList<Plant> result = new ArrayList<>();


        int count = 0;
        for (int i = 0; i < plantList.size(); i++) {
            count = 0;
            if (plantList.get(i).getSmell().equals(onBoardingAnswer.get(0).getOptionIdx())) count++;
            if (plantList.get(i).getHeight().equals(onBoardingAnswer.get(1).getOptionIdx())) count++;
            if (plantList.get(i).getSpeed().equals(onBoardingAnswer.get(2).getOptionIdx())) count++;
            if (plantList.get(i).getManagementLevel().equals(onBoardingAnswer.get(3).getOptionIdx())) count++;
            switch (count) {
                case 2:
                    two.add(plantList.get(i));
                    break;
                case 3:
                    three.add(plantList.get(i));
                    break;
                case 4:
                    four.add(plantList.get(i));
                    break;
            }

            countList[i] = count;
        }

        result.addAll(four);
        result.addAll(three);
        result.addAll(two);

        List<ResRecoPlantDto> resRecoPlantDtos = result
                .stream()
                .map(ResRecoPlantDto::of)
                .collect(Collectors.toList());
        LOGGER.info("길이: "+countList.length);
        for(int i = 0; i < resRecoPlantDtos.size(); i++) {
            resRecoPlantDtos.get(i).setLike(favoritesRepository.existsByPlantIdxAndUserIdx(resRecoPlantDtos.get(i).getPlantIdx(), userIdx));

            LOGGER.info("plantIdx : "+resRecoPlantDtos.get(i).getPlantIdx());
            resRecoPlantDtos.get(i).setCount(countList[resRecoPlantDtos.get(i).getPlantIdx()-1]);
        }


        return resRecoPlantDtos;
    }

    /**
     * 찜하기
     */

    public boolean postLike(Integer userIdx, Integer plantIdx) {

        Favorites favorites = Favorites.from(plantIdx, userIdx);

        if (favoritesRepository.existsByPlantIdxAndUserIdx(plantIdx, userIdx)) { // 이미 찜했는지 확인

            Favorites favoritesOptional = favoritesRepository.findByPlantIdxAndUserIdx(plantIdx, userIdx);

            favoritesRepository.delete(favoritesOptional);

            return true;
        } else {
            favoritesRepository.save(favorites);

            return false;
        }

    }
}

