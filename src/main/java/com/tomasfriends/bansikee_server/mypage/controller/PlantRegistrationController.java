package com.tomasfriends.bansikee_server.mypage.controller;

import com.tomasfriends.bansikee_server.mypage.service.PlantRegisterService;
import com.tomasfriends.bansikee_server.mypage.service.dto.PlantRegistrationRequestDto;
import com.tomasfriends.bansikee_server.response.dto.SuccessCode;
import com.tomasfriends.bansikee_server.response.dto.SuccessResponse;
import com.tomasfriends.bansikee_server.response.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = {"6. 내 식물로 등록"})
@RestController
@RequiredArgsConstructor
public class PlantRegistrationController {

    private final PlantRegisterService plantRegisterService;
    private final ResponseService responseService;

    @ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "내식물로 등록", notes = "토큰 인증 필요")
    @PostMapping("/myplant/registration")
    public ResponseEntity<SuccessResponse> registerPlant(@RequestBody @Valid PlantRegistrationRequestDto plantRegistrationRequestDto) {
        plantRegisterService.save(plantRegistrationRequestDto);
        return responseService.getSuccessResult(SuccessCode.PLANT_REGISTER_SUCCESS);
    }

//    @ApiImplicitParams({
//        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
//    })
//    @ApiOperation(value = "내식물 리스트 조회", notes = "토큰 인증 필요")
//    @GetMapping("/myplant")
//    public ResponseEntity<> registerPlant() {
//        plantRegisterService.findAll();
//        return responseService.getSuccessResult(SuccessCode.PLANT_REGISTER_SUCCESS);
//    }

//    @ApiImplicitParams({
//        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
//    })
//    @ApiOperation(value = "내식물로 등록", notes = "토큰 인증 필요")
//    @PostMapping("/registration/plant")
//    public ResponseEntity<SuccessResponse> registerPlant(@RequestBody @Valid PlantRegistrationRequestDto plantRegistrationRequestDto) {
//        plantRegisterService.save(plantRegistrationRequestDto);
//        return responseService.getSuccessResult(SuccessCode.PLANT_REGISTER_SUCCESS);
//    }
//    @ApiImplicitParams({
//        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
//    })
//
//    @ApiOperation(value = "내식물로 등록", notes = "토큰 인증 필요")
//    @PostMapping("/registration/plant")
//    public ResponseEntity<SuccessResponse> registerPlant(@RequestBody @Valid PlantRegistrationRequestDto plantRegistrationRequestDto) {
//        plantRegisterService.save(plantRegistrationRequestDto);
//        return responseService.getSuccessResult(SuccessCode.PLANT_REGISTER_SUCCESS);
//    }
}
