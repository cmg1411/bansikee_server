package com.tomasfriends.bansikee_server.dictionary.dto.dictionaryDto;
import com.tomasfriends.bansikee_server.dictionary.domain.SearchHistory;
import lombok.Data;

@Data
public class ResSearchHistoryDto {
    private Integer plantIdx;

    private String plantImgUrl;

    private String name;

    private String species;

    private String info;

    private boolean like;

    public static ResSearchHistoryDto of(SearchHistory searchHistory){

        ResSearchHistoryDto resSearchHistoryDto = new ResSearchHistoryDto();

        resSearchHistoryDto.plantIdx = searchHistory.getPlant().getPlantIdx();
        resSearchHistoryDto.plantImgUrl = searchHistory.getPlant().getPlantImgUrl();
        resSearchHistoryDto.name = searchHistory.getPlant().getName();
        resSearchHistoryDto.species = searchHistory.getPlant().getSpecies();
        resSearchHistoryDto.info = searchHistory.getPlant().getInfo();

        return resSearchHistoryDto;
    }
}
