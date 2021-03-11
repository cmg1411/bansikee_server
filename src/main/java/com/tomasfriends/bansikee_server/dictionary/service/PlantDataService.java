package com.tomasfriends.bansikee_server.dictionary.service;


import com.tomasfriends.bansikee_server.dictionary.domain.PlantDictionary;
import com.tomasfriends.bansikee_server.dictionary.dto.*;
import com.tomasfriends.bansikee_server.dictionary.repository.PlantDictionaryRepository;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

import java.util.List;


@Transactional
public class PlantDataService {

    private final PlantDictionaryRepository plantDictionaryRepository;

    public PlantDataService(PlantDictionaryRepository plantDictionaryRepository){
        this.plantDictionaryRepository = plantDictionaryRepository;
    }


    public void getPlantList() {
        RestTemplate restTemplate = new RestTemplate();
/**
 * 식물 리스트 가져오기
 * */

            String listUrl = "http://api.nongsaro.go.kr/service/garden/gardenList";
            String apiKey = "?apiKey=" + "20210201J5NSE6X2HGN0P6UUOEJEVQ";
            String numOfRows = "&numOfRows=" + 217;

            String listUri = listUrl + apiKey + numOfRows;

            NongsaroResDto nongsaroResponse = restTemplate.getForObject(listUri, NongsaroResDto.class);

            List<ItemDto> items = nongsaroResponse.getBody().getItems();


//            for (ItemDto item : items) {
//                PlantDictionary plantDictionary = PlantDictionary.of(item.getCntntsNo(), item.getCntntsSj());
//
//                plantDictionaryRepository.save(plantDictionary);
//            }

/**
 * 식물 내용 가져오기
 * */
        for (ItemDto item : items) {

            Integer code = item.getCntntsNo();
            String url = "http://api.nongsaro.go.kr/service/garden/gardenDtl";
            String cntntsNo = "&cntntsNo=" + code;

            String uri = url + apiKey + cntntsNo;

            PlantDataResDto plantDataResponse = restTemplate.getForObject(uri, PlantDataResDto.class);

            List<PlantDto> plants = plantDataResponse.getPlants();

            PlantDto plant = plants.get(0);

                PlantDictionary plantDictionary = PlantDictionary.of(item.getCntntsSj(), plant.getFmlCodeNm(),plant.getSpeclmanageInfo(),plant.getManagedemanddoCodeNm(),plant.getGrowthHgInfo(),plant.getGrowthAraInfo(),plant.getGrwtveCodeNm(),plant.getSmellCodeNm(),plant.getGrwhTpCodeNm(),plant.getWatercycleSprngCodeNm(),plant.getLighttdemanddoCodeNm(),plant.getPostngplaceCodeNm(),item.getRtnStreFileNm());

                plantDictionaryRepository.save(plantDictionary);

        }
    }
}
