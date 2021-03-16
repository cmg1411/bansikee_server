package com.tomasfriends.bansikee_server.dictionary.controller;

import com.tomasfriends.bansikee_server.dictionary.dto.dictionaryDto.ResPlantDto;
import com.tomasfriends.bansikee_server.dictionary.service.DictionaryService;
import com.tomasfriends.bansikee_server.response.dto.SingleDataSuccessResponse;
import com.tomasfriends.bansikee_server.response.dto.SuccessCode;
import com.tomasfriends.bansikee_server.response.service.ResponseService;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

//    @ApiOperation(value = "식물 리스트 조회", notes = " ")
//    @PostMapping("/on-boarding")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인시 발급 받는 access_token", required = true, dataType = "String", paramType = "header")
//    })
//    public ResponseEntity<SuccessResponse> postAnswer(@RequestBody ReqAnswerListDto reqAnswerDto) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        BansikeeUser principal = (BansikeeUser) authentication.getPrincipal();
//        Integer userIdx = principal.getId();
//        onBoardingService.postAnswer(userIdx, reqAnswerDto);
//        return responseService.getSuccessResult(SuccessCode.ON_BOARDING_RESULT_SUCCESS);
//    }

    @ApiOperation(value = "식물 상세 조회", notes = " ")
    @GetMapping("/plant/{plantidx}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인시 발급 받는 access_token", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity<SingleDataSuccessResponse<ResPlantDto>> getPlant(@PathVariable("plantidx") Integer plantIdx) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BansikeeUser principal = (BansikeeUser) authentication.getPrincipal();
        Integer userIdx = principal.getId();

        return responseService.getSingleResult(dictionaryService.getPlant(userIdx, plantIdx),SuccessCode.GET_PLANTINFO);
    }




//    @GetMapping("/plant-list")
//    public getList(){
//
//
//

//    }
}
