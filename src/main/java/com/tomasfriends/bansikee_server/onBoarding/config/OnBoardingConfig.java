package com.tomasfriends.bansikee_server.onBoarding.config;

import com.tomasfriends.bansikee_server.onBoarding.repository.OnBoardingAnswerRepository;
import com.tomasfriends.bansikee_server.onBoarding.repository.FavoritesRepository;
import com.tomasfriends.bansikee_server.onBoarding.repository.PlantRepository;
import com.tomasfriends.bansikee_server.onBoarding.repository.QuestionRepository;
import com.tomasfriends.bansikee_server.onBoarding.service.OnBoardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OnBoardingConfig {

    private final QuestionRepository questionRepository;
//    private final JpaOptionsRepository optionsRepository;
    private final OnBoardingAnswerRepository onBoardingAnswerRepository;
    private final FavoritesRepository favoritesRepository;
    private final PlantRepository plantRepository;

    @Autowired
    public OnBoardingConfig(QuestionRepository questionRepository, OnBoardingAnswerRepository onBoardingAnswerRepository, FavoritesRepository favoritesRepository,PlantRepository plantRepository) {
        this.questionRepository = questionRepository;
//        this.optionsRepository = optionsRepository;
        this.onBoardingAnswerRepository = onBoardingAnswerRepository;
        this.favoritesRepository = favoritesRepository;
        this.plantRepository = plantRepository;
    }

    @Bean
    public OnBoardingService questionService(){
        return new OnBoardingService(questionRepository, onBoardingAnswerRepository, favoritesRepository, plantRepository);
    }

//    @Bean
//    public QuestionRepository questionRepository(){
//
//    }

}
