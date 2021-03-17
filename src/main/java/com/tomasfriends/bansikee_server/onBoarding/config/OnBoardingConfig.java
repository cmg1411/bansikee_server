package com.tomasfriends.bansikee_server.onBoarding.config;

import com.tomasfriends.bansikee_server.onBoarding.repository.*;
import com.tomasfriends.bansikee_server.onBoarding.service.OnBoardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OnBoardingConfig {

    private final QuestionRepository questionRepository;
    private final OnBoardingAnswerRepository onBoardingAnswerRepository;
    private final FavoritesRepository favoritesRepository;
    private final PlantRepository plantRepository;
    private final OnBoardUserRepository onBoardUserRepository;

    @Autowired
    public OnBoardingConfig(QuestionRepository questionRepository, OnBoardingAnswerRepository onBoardingAnswerRepository, FavoritesRepository favoritesRepository,PlantRepository plantRepository, OnBoardUserRepository onBoardUserRepository) {
        this.questionRepository = questionRepository;
        this.onBoardingAnswerRepository = onBoardingAnswerRepository;
        this.favoritesRepository = favoritesRepository;
        this.plantRepository = plantRepository;
        this.onBoardUserRepository = onBoardUserRepository;
    }

    @Bean
    public OnBoardingService questionService(){
        return new OnBoardingService(questionRepository, onBoardingAnswerRepository, favoritesRepository, plantRepository, onBoardUserRepository);
    }

//    @Bean
//    public QuestionRepository questionRepository(){
//
//    }

}
