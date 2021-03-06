package com.tomasfriends.bansikee_server.onBoarding.config;

import com.tomasfriends.bansikee_server.onBoarding.repository.AnswerRepository;
import com.tomasfriends.bansikee_server.onBoarding.repository.FavoritesRepository;
import com.tomasfriends.bansikee_server.onBoarding.repository.QuestionRepository;
import com.tomasfriends.bansikee_server.onBoarding.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OnBoardingConfig {

    private final QuestionRepository questionRepository;
//    private final JpaOptionsRepository optionsRepository;
    private final AnswerRepository answerRepository;
    private final FavoritesRepository favoritesRepository;

    @Autowired
    public OnBoardingConfig(QuestionRepository questionRepository, AnswerRepository answerRepository, FavoritesRepository favoritesRepository) {
        this.questionRepository = questionRepository;
//        this.optionsRepository = optionsRepository;
        this.answerRepository = answerRepository;
        this.favoritesRepository = favoritesRepository;
    }

    @Bean
    public QuestionService questionService(){
        return new QuestionService(questionRepository,  answerRepository, favoritesRepository);
    }

//    @Bean
//    public QuestionRepository questionRepository(){
//
//    }

}
