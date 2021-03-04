package com.tomasfriends.bansikee_server;

import com.tomasfriends.bansikee_server.repository.OptionsRepository;
import com.tomasfriends.bansikee_server.repository.QuestionRepository;
import com.tomasfriends.bansikee_server.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final QuestionRepository questionRepository;
    private final OptionsRepository optionsRepository;

    @Autowired
    public SpringConfig(QuestionRepository questionRepository, OptionsRepository optionsRepository) {
        this.questionRepository = questionRepository;
        this.optionsRepository = optionsRepository;
    }

    @Bean
    public QuestionService questionService(){
        return new QuestionService(questionRepository);
    }

//    @Bean
//    public QuestionRepository questionRepository(){
//
//    }

}
