package com.tomasfriends.bansikee_server.mypage.controller;

import com.tomasfriends.bansikee_server.mypage.service.DiaryService;
import com.tomasfriends.bansikee_server.mypage.service.dto.DiaryRequestDto;
import com.tomasfriends.bansikee_server.response.dto.SuccessCode;
import com.tomasfriends.bansikee_server.response.dto.SuccessResponse;
import com.tomasfriends.bansikee_server.response.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = {"7. 일기"})
@RestController
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;
    private final ResponseService responseService;

    @ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "일지 작성", notes = "토큰 인증 필요")
    @PostMapping("/registration/diary")
    public ResponseEntity<SuccessResponse> registerDiary(@RequestBody @Valid DiaryRequestDto diaryRequestDto) {
        diaryService.save(diaryRequestDto);
        return responseService.getSuccessResult(SuccessCode.PLANT_REGISTER_SUCCESS);
    }
}
