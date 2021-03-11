package com.tomasfriends.bansikee_server.dictionary.controller;

import com.tomasfriends.bansikee_server.dictionary.service.PlantDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlantDataController {

    private final PlantDataService plantDataService;

    @Autowired
    public PlantDataController(PlantDataService plantDataService){
        this.plantDataService = plantDataService;
    }

    @GetMapping("/plant-list")
    public ResponseEntity<String> getPlantList(){
        plantDataService.getPlantList();
        return new ResponseEntity<>("저장 성공", HttpStatus.OK);
    }

}
