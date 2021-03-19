package com.tomasfriends.bansikee_server.dictionary.domain;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@IdClass(SearchHistoryPK.class)
@Table(name = "searchHistory")
public class SearchHistory {

    @Id
    private Integer userIdx;

    @Id
    private Integer plantIdx;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "plantIdx",insertable = false, updatable = false)
    private PlantDictionary plant;

    public static SearchHistory of(Integer userIdx, Integer plantIdx){
        SearchHistory searchHistory = new SearchHistory();

        searchHistory.userIdx = userIdx;
        searchHistory.plantIdx = plantIdx;

        return searchHistory;

    }


}
