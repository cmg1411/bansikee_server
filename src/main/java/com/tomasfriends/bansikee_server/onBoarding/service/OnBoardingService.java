package com.tomasfriends.bansikee_server.onBoarding.service;

import com.tomasfriends.bansikee_server.onBoarding.domain.Favorites;
import com.tomasfriends.bansikee_server.onBoarding.domain.OnBoardingAnswer;
import com.tomasfriends.bansikee_server.onBoarding.domain.Plant;
import com.tomasfriends.bansikee_server.onBoarding.domain.User;
import com.tomasfriends.bansikee_server.onBoarding.dto.*;
import com.tomasfriends.bansikee_server.onBoarding.repository.*;

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
    private OnBoardUserRepository onBoardUserRepository;

    public OnBoardingService(QuestionRepository questionRepository, OnBoardingAnswerRepository onBoardingAnswerRepository, FavoritesRepository favoritesRepository, PlantRepository plantRepository, OnBoardUserRepository onBoardUserRepository) {
        this.questionRepository = questionRepository;
        this.onBoardingAnswerRepository = onBoardingAnswerRepository;
        this.favoritesRepository = favoritesRepository;
        this.plantRepository = plantRepository;
        this.onBoardUserRepository = onBoardUserRepository;
    }


    /**
     * 전체 질문 조회
     */
    public List<ResQuestionDto> getQuestions(Integer userIdx) {

        //userIdx로 onBoarding 조회
        List<User> user = onBoardUserRepository.findByUseridx(userIdx);
        if(user.get(0).getOnBoarding() == 0) {
            user.get(0).setOnBoarding(1);

            onBoardUserRepository.save(user.get(0));
        }

        List<ResQuestionDto> resQuestionDtos = questionRepository.findAll()
                .stream()
                .map(ResQuestionDto::of)
                .collect(Collectors.toList());

        String[] questionContent =resQuestionDtos.get(3).getQuestionContent().split("\\n");
        resQuestionDtos.get(3).setQuestionContent(questionContent[0] + "\n"+user.get(0).getName()+questionContent[1]);

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

            if (plantList.get(i).getSmellCode().equals(onBoardingAnswer.get(0).getOptionIdx())) count++;
            if (plantList.get(i).getHeightCode().equals(onBoardingAnswer.get(1).getOptionIdx())) count++;
            if (plantList.get(i).getSpeedCode().equals(onBoardingAnswer.get(2).getOptionIdx())) count++;
            if (plantList.get(i).getManagementLevelCode().equals(onBoardingAnswer.get(3).getOptionIdx())) count++;
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

        for(int i = 0; i < resRecoPlantDtos.size(); i++) {
            resRecoPlantDtos.get(i).setLike(favoritesRepository.existsByPlantIdxAndUserIdx(resRecoPlantDtos.get(i).getPlantIdx(), userIdx));
            resRecoPlantDtos.get(i).setCount(countList[resRecoPlantDtos.get(i).getPlantIdx()-1]);
            resRecoPlantDtos.get(i).setPlantImgUrl("http://www.nongsaro.go.kr/cms_contents/301/"+resRecoPlantDtos.get(i).getPlantImgUrl().split("\\|")[0]);
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

