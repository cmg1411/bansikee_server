package com.tomasfriends.bansikee_server.dictionary.service;

import com.tomasfriends.bansikee_server.dictionary.domain.PlantDictionary;
import com.tomasfriends.bansikee_server.dictionary.domain.SearchHistory;
import com.tomasfriends.bansikee_server.dictionary.dto.dictionaryDto.ResPlantDto;
import com.tomasfriends.bansikee_server.dictionary.dto.dictionaryDto.ResPlantListDto;
import com.tomasfriends.bansikee_server.dictionary.dto.dictionaryDto.ResSearchHistoryDto;
import com.tomasfriends.bansikee_server.dictionary.repository.FavoritesDictionaryRepository;
import com.tomasfriends.bansikee_server.dictionary.repository.PlantDictionaryRepository;
import com.tomasfriends.bansikee_server.dictionary.repository.SearchHistoryRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Transactional
public class DictionaryService {

    private PlantDictionaryRepository plantDictionaryRepository;
    private FavoritesDictionaryRepository favoritesDictionaryRepository;
    private SearchHistoryRepository searchHistoryRepository;

    public DictionaryService(PlantDictionaryRepository plantDictionaryRepository, FavoritesDictionaryRepository favoritesDictionaryRepository, SearchHistoryRepository searchHistoryRepository) {
        this.plantDictionaryRepository = plantDictionaryRepository;
        this.favoritesDictionaryRepository = favoritesDictionaryRepository;
        this.searchHistoryRepository = searchHistoryRepository;
    }
    private static final int PAGE_POST_COUNT = 10;
    public List<ResPlantListDto> getPlantList(Integer userIdx, String keyWord, Integer pageNum, String sortBy){
        Pageable pageable = PageRequest.of(pageNum, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "name"));
        List<PlantDictionary> result;
        if(sortBy.equals("popularity")){
            result = plantDictionaryRepository.findByFavorites(keyWord, pageable);
        }
        else {
            result = plantDictionaryRepository.findByNameOrSpeciesContaining(keyWord, keyWord, pageable);
        }

        List<ResPlantListDto> resPlantListDto = result
                .stream()
                .map(ResPlantListDto::of)
                .collect(Collectors.toList());

        for(int i = 0; i < resPlantListDto.size(); i++) {
            resPlantListDto.get(i).setLike(favoritesDictionaryRepository.existsByPlantIdxAndUserIdx(resPlantListDto.get(i).getPlantIdx(), userIdx));
            resPlantListDto.get(i).setPlantImgUrl("http://www.nongsaro.go.kr/cms_contents/301/"+resPlantListDto.get(i).getPlantImgUrl().split("\\|")[0]);

        }
        return resPlantListDto;
    }

    public ResPlantDto getPlant(Integer userIdx, Integer plantIdx, String status){

        // 검색해 들어온 것만 저장
        if(status.equals("search")){
            SearchHistory searchHistory = SearchHistory.of(userIdx,plantIdx);
            searchHistoryRepository.save(searchHistory);
        }

        Optional<PlantDictionary> plant = plantDictionaryRepository.findById(plantIdx);

        List<ResPlantDto> resPlantDtos = plant
                .stream()
                .map(ResPlantDto::of)
                .collect(Collectors.toList());
        resPlantDtos.get(0).setPlantImgUrl("http://www.nongsaro.go.kr/cms_contents/301/"+resPlantDtos.get(0).getPlantImgUrl().split("\\|")[0]);
        resPlantDtos.get(0).setLike(favoritesDictionaryRepository.existsByPlantIdxAndUserIdx( plantIdx, userIdx));

        return resPlantDtos.get(0);
    }

    public List<ResSearchHistoryDto> getSearchHistory(Integer userIdx){

        List<SearchHistory> searchHistoryResult = searchHistoryRepository.findByUserIdxOrderByUpdatedAtDesc(userIdx);

        List<ResSearchHistoryDto> resSearchHistoryDtos = searchHistoryResult
                .stream()
                .map(ResSearchHistoryDto::of)
                .collect(Collectors.toList());

        for(int i = 0; i < resSearchHistoryDtos.size(); i++) {
            resSearchHistoryDtos.get(i).setLike(favoritesDictionaryRepository.existsByPlantIdxAndUserIdx(resSearchHistoryDtos.get(i).getPlantIdx(), userIdx));
            resSearchHistoryDtos.get(i).setPlantImgUrl("http://www.nongsaro.go.kr/cms_contents/301/"+resSearchHistoryDtos.get(i).getPlantImgUrl().split("\\|")[0]);

        }

        return resSearchHistoryDtos;
    }

    public void deleteSearchHistory(){
        LocalDateTime subDate = LocalDateTime.now().minusDays(5);
        searchHistoryRepository.deleteByUpdatedAtIsBefore(subDate);
    }
}
