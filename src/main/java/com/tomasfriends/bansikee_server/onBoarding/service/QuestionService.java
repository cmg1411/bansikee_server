package com.tomasfriends.bansikee_server.onBoarding.service;

import com.tomasfriends.bansikee_server.onBoarding.domain.Favorites;
import com.tomasfriends.bansikee_server.onBoarding.domain.OnBoardingAnswer;
import com.tomasfriends.bansikee_server.onBoarding.dto.ReqAnswerDto;
import com.tomasfriends.bansikee_server.onBoarding.dto.ResQuestionDto;
import com.tomasfriends.bansikee_server.onBoarding.dto.ResRecoPlantListDto;
import com.tomasfriends.bansikee_server.onBoarding.repository.AnswerRepository;
import com.tomasfriends.bansikee_server.onBoarding.repository.FavoritesRepository;
import com.tomasfriends.bansikee_server.onBoarding.repository.QuestionRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
public class QuestionService {

//    private JpaOptionsRepository optionsRepository;
    private  QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private FavoritesRepository favoritesRepository;

    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository, FavoritesRepository favoritesRepository){
        this.questionRepository = questionRepository;
//        this.optionsRepository = optionsRepository;
        this.answerRepository = answerRepository;
        this.favoritesRepository = favoritesRepository;
    }


    /**
     * 전체 질문 조회
     */
    public List<ResQuestionDto> getQuestions() {
//        ResQuestionListDto resQuestionListDto = new ResQuestionListDto();
//        List <ResQuestionDto> resQuestionDto =  List <ResQuestionDto>();
//        ResOptionDto resOptionDto = new ResOptionDto();
//
//        //question
//
////        questionRepository.findAll().size()
//        for ( int i = 0 ; i < questionRepository.findAll().size(); i++){
//
//            resQuestionDto.setQuestionIdx(questionRepository.findAll().get(i).getQuestionIdx());
//            resQuestionDto.setQuestionContent(questionRepository.findAll().get(i).getContent());
//
//            resQuestionListDto.addQuestionDto(resQuestionDto);
//
//        }
////option
//        for(int j = 0 ; j < 3; j++){
//            resOptionDto.setOptionIdx(optionsRepository.findByQuestionIdx(2).get(j).getOptionsIdx());
//            resOptionDto.setOptionContent(optionsRepository.findByQuestionIdx(2).get(j).getContent());
//
//            resQuestionDto.addOptionDto(resOptionDto);
//        }


//        return resQuestionDto;

//        return questionRepository.findAll();

//        List <ResOptionDto> resOptionDtos = optionsRepository.findAll()
//                .stream()
//                .map(ResOptionDto::of)
//                .collect(Collectors.toList());

        List <ResQuestionDto> resQuestionDtos = questionRepository.findAll()
                .stream()
                .map(ResQuestionDto::of)
                .collect(Collectors.toList());

        return resQuestionDtos;
    }

    /**
     * 결과 DB에 저장
     * */
    public void postAnswer(Integer userIdx, ReqAnswerDto reqAnswerDto){

        OnBoardingAnswer onBoardingAnswer = OnBoardingAnswer.from(userIdx, reqAnswerDto.getQuestionIdx(), reqAnswerDto.getOptionIdx());

        answerRepository.save(onBoardingAnswer);

    }

    /**
     * 추천 식물 리스트 가져오기
     */
    public ResRecoPlantListDto getRecommendPlants(Integer userIdx){

        ResRecoPlantListDto resRecoPlantListDto = new ResRecoPlantListDto();

        return resRecoPlantListDto;
    }

    /**
     * 찜하기
     */

    public void postLike(Integer userIdx, Integer plantIdx ){

        Favorites favorites = Favorites.from( plantIdx, userIdx);

        if(favoritesRepository.existsByPlantIdxAndUserIdx(plantIdx, userIdx)){ // 이미 찜했는지 확인

            Favorites favoritesOptional = favoritesRepository.findByPlantIdxAndUserIdx(plantIdx, userIdx);

            favoritesRepository.delete(favoritesOptional);
        }else{
            favoritesRepository.save(favorites);
        }

    }
}

