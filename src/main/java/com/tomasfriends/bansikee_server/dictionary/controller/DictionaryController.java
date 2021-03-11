package com.tomasfriends.bansikee_server.dictionary.controller;

import com.tomasfriends.bansikee_server.dictionary.service.DictionaryService;
import com.tomasfriends.bansikee_server.response.service.ResponseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"5.식물사전"})
@RestController
public class DictionaryController {

    private final DictionaryService dictionaryService;
    private final ResponseService responseService;

    @Autowired
    public DictionaryController(DictionaryService dictionaryService, ResponseService responseService){
        this.dictionaryService = dictionaryService;
        this.responseService = responseService;
    }




//    @GetMapping("/plant-list")
//    public getList(){
//
//
//

//    }
}
