package com.tomasfriends.bansikee_server.dictionary.controller;

import com.tomasfriends.bansikee_server.dictionary.dto.dictionaryDto.ResPlantDto;
import com.tomasfriends.bansikee_server.dictionary.dto.dictionaryDto.ResPlantListDto;
import com.tomasfriends.bansikee_server.dictionary.dto.dictionaryDto.ResSearchHistoryDto;
import com.tomasfriends.bansikee_server.dictionary.service.DictionaryService;
import com.tomasfriends.bansikee_server.response.dto.SingleDataSuccessResponse;
import com.tomasfriends.bansikee_server.response.dto.SuccessCode;
import com.tomasfriends.bansikee_server.response.dto.SuccessResponse;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"5.식물사전"})
@RestController
public class DictionaryController {

    private final DictionaryService dictionaryService;
    private final ResponseService responseService;

    @Autowired
    public DictionaryController(DictionaryService dictionaryService, ResponseService responseService) {
        this.dictionaryService = dictionaryService;
        this.responseService = responseService;
    }

    @ApiOperation(value = "식물 리스트 조회", notes = " ")
    @GetMapping("/plants")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인시 발급 받는 access_token", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity<SingleDataSuccessResponse<List<ResPlantListDto>>> getPlantList(@RequestParam("keyWord") String keyWord,
                                                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                                       @RequestParam("sortBy") String sortBy) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BansikeeUser principal = (BansikeeUser) authentication.getPrincipal();
        Integer userIdx = principal.getId();
        return responseService.getSingleResult(dictionaryService.getPlantList(userIdx, keyWord, pageNum - 1, sortBy), SuccessCode.GET_PLANTLIST);
    }

    @ApiOperation(value = "식물 상세 조회", notes = " ")
    @GetMapping("/plant/{plantidx}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인시 발급 받는 access_token", required = true, dataType = "String", paramType = "header")
    })
    // 상세정보 조회 기록 저장
    public ResponseEntity<SingleDataSuccessResponse<ResPlantDto>> getPlant(@PathVariable("plantidx") Integer plantIdx,@RequestParam("status") String status) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BansikeeUser principal = (BansikeeUser) authentication.getPrincipal();
        Integer userIdx = principal.getId();

        return responseService.getSingleResult(dictionaryService.getPlant(userIdx, plantIdx,status), SuccessCode.GET_PLANTINFO);
    }

    //최근 검색 기록
    @ApiOperation(value = "식물 검색 기록 조회", notes = " ")
    @GetMapping("/plants-search")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인시 발급 받는 access_token", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity<SingleDataSuccessResponse<List<ResSearchHistoryDto>>> getSearchHistory(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BansikeeUser principal = (BansikeeUser) authentication.getPrincipal();
        Integer userIdx = principal.getId();

        return responseService.getSingleResult(dictionaryService.getSearchHistory(userIdx), SuccessCode.GET_SEARCH_HISTORY_SUCCESS);
    }

        //검색 기록 모두 삭제
    @ApiOperation(value = "식물 검색 기록 모두 삭제", notes = " ")
    @DeleteMapping("/plant-histories")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인시 발급 받는 access_token", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity<SuccessResponse> deleteSearchHistories(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BansikeeUser principal = (BansikeeUser) authentication.getPrincipal();
        Integer userIdx = principal.getId();
        dictionaryService.deleteSearchHistories(userIdx);
        return responseService.getSuccessResult(SuccessCode.DELETE_ALL_SEARCH_HISTORY_SUCCESS);
    }

    //검색 기록 개별 삭제
    @ApiOperation(value = "식물 검색 기록 개별 삭제", notes = " ")
    @DeleteMapping("/plant-history/{plantidx}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인시 발급 받는 access_token", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity<SuccessResponse> deleteSearchHistory(@PathVariable("plantidx") Integer plantIdx){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BansikeeUser principal = (BansikeeUser) authentication.getPrincipal();
        Integer userIdx = principal.getId();
        dictionaryService.deleteSearchHistory(userIdx,plantIdx);
        return responseService.getSuccessResult(SuccessCode.DELETE_SEARCH_HISTORY_SUCCESS);
    }
}
