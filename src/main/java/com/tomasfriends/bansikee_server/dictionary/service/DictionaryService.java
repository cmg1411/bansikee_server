package com.tomasfriends.bansikee_server.dictionary.service;

import com.tomasfriends.bansikee_server.dictionary.domain.PlantDictionary;
import com.tomasfriends.bansikee_server.dictionary.dto.dictionaryDto.ResPlantDto;
import com.tomasfriends.bansikee_server.dictionary.dto.dictionaryDto.ResPlantListDto;
import com.tomasfriends.bansikee_server.dictionary.repository.FavoritesDictionaryRepository;
import com.tomasfriends.bansikee_server.dictionary.repository.PlantDictionaryRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
public class DictionaryService {

    private PlantDictionaryRepository plantDictionaryRepository;
    private FavoritesDictionaryRepository favoritesDictionaryRepository;

    public DictionaryService(PlantDictionaryRepository plantDictionaryRepository, FavoritesDictionaryRepository favoritesDictionaryRepository) {
        this.plantDictionaryRepository = plantDictionaryRepository;
        this.favoritesDictionaryRepository = favoritesDictionaryRepository;
    }

//    public List<ResPlantListDto> getPlantList(Integer userIdx){
//        List<ResPlantListDto> resPlantListDto = new ResPlantListDto();
//        return resPlantListDto;
//
//    }

    public ResPlantDto getPlant(Integer userIdx, Integer plantIdx){

        Optional<PlantDictionary> plant = plantDictionaryRepository.findById(plantIdx);

        List<ResPlantDto> resPlantDtos = plant
                .stream()
                .map(ResPlantDto::of)
                .collect(Collectors.toList());

        resPlantDtos.get(0).setPlantImgUrl("http://www.nongsaro.go.kr/cms_contents/301/"+resPlantDtos.get(0).getPlantImgUrl().split("\\|")[0]);
        resPlantDtos.get(0).setLike(favoritesDictionaryRepository.existsByPlantIdxAndUserIdx( plantIdx, userIdx));

        return resPlantDtos.get(0);
    }
}
