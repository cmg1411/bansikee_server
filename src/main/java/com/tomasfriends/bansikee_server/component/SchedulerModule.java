package com.tomasfriends.bansikee_server.component;

import com.tomasfriends.bansikee_server.dictionary.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SchedulerModule {

    private final DictionaryService dictionaryService;

    @Autowired
    public SchedulerModule(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }


    private static Logger logger = LoggerFactory.getLogger(SchedulerModule.class);

    @Scheduled(fixedDelay = 1000*60*60)
    public void deleteSearchHistory() {
        dictionaryService.deleteSearchHistoryScheduler();
        logger.info("5일 지난 히스토리 삭제", new Date());
    }
}
