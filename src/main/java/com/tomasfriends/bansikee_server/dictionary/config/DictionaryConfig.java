package com.tomasfriends.bansikee_server.dictionary.config;

import com.tomasfriends.bansikee_server.dictionary.repository.PlantDictionaryRepository;
import com.tomasfriends.bansikee_server.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DictionaryConfig {

    private final PlantDictionaryRepository plantDictionaryRepository;

    @Autowired
    public DictionaryConfig(PlantDictionaryRepository plantDictionaryRepository){
        this.plantDictionaryRepository = plantDictionaryRepository;
    }

    @Bean
    public DictionaryService dictionaryService(){
        return new DictionaryService();
    }

//    @Bean
//    public PlantDataService plantDataService(){
//        return new PlantDataService(plantDictionaryRepository);
//    }
}
