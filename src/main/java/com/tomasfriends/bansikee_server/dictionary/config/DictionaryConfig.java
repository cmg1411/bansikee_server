package com.tomasfriends.bansikee_server.dictionary.config;

import com.tomasfriends.bansikee_server.dictionary.repository.FavoritesDictionaryRepository;
import com.tomasfriends.bansikee_server.dictionary.repository.PlantDictionaryRepository;
import com.tomasfriends.bansikee_server.dictionary.repository.SearchHistoryRepository;
import com.tomasfriends.bansikee_server.dictionary.service.DictionaryService;
//import com.tomasfriends.bansikee_server.dictionary.service.PlantDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DictionaryConfig {

    private final PlantDictionaryRepository plantDictionaryRepository;
    private final FavoritesDictionaryRepository favoritesDictionaryRepository;
    private final SearchHistoryRepository searchHistoryRepository;

    @Autowired
    public DictionaryConfig(PlantDictionaryRepository plantDictionaryRepository, FavoritesDictionaryRepository favoritesDictionaryRepository,SearchHistoryRepository searchHistoryRepository){
        this.plantDictionaryRepository = plantDictionaryRepository;
        this.favoritesDictionaryRepository = favoritesDictionaryRepository;
        this.searchHistoryRepository = searchHistoryRepository;
    }

    @Bean
    public DictionaryService dictionaryService(){
        return new DictionaryService(plantDictionaryRepository, favoritesDictionaryRepository, searchHistoryRepository);
    }

//    @Bean
//    public PlantDataService plantDataService(){
//        return new PlantDataService(plantDictionaryRepository);
//    }
}
