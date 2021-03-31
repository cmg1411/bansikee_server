package com.tomasfriends.bansikee_server.mypage.controller;

import com.tomasfriends.bansikee_server.mypage.service.PlantRegisterService;
import com.tomasfriends.bansikee_server.mypage.service.dto.resp.MyPlantListResponseDto;
import com.tomasfriends.bansikee_server.mypage.service.dto.req.MyPlantPatchRequestDto;
import com.tomasfriends.bansikee_server.mypage.service.dto.resp.MyPlantResponseDto;
import com.tomasfriends.bansikee_server.mypage.service.dto.req.PlantRegistrationRequestDto;
import com.tomasfriends.bansikee_server.response.dto.ListDataSuccessResponse;
import com.tomasfriends.bansikee_server.response.dto.SingleDataSuccessResponse;
import com.tomasfriends.bansikee_server.response.dto.SuccessCode;
import com.tomasfriends.bansikee_server.response.dto.SuccessResponse;
import com.tomasfriends.bansikee_server.response.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "내 식물 리스트 조회", notes = "토큰 인증 필요")
    @GetMapping("/myplant")
    public ResponseEntity<ListDataSuccessResponse<MyPlantListResponseDto>> findMyPlantList() {
        List<MyPlantListResponseDto> myAllPlantList = plantRegisterService.findAll();
        return responseService.getListResult(myAllPlantList, SuccessCode.MY_ALL_PLANT_READ_SUCCESS);
    }

    @ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "내 식물 상세 조회", notes = "토큰 인증 필요")
    @GetMapping("/myplant/{myPlantId}")
    public ResponseEntity<SingleDataSuccessResponse<MyPlantResponseDto>> findMyPlant(@PathVariable("myPlantId") Integer myPlantId) {
        MyPlantResponseDto plant = plantRegisterService.findPlant(myPlantId);
        return responseService.getSingleResult(plant, SuccessCode.PLANT_READ_SUCCESS);
    }

    @ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "내 식물 정보 수정", notes = "토큰 인증 필요")
    @PatchMapping("/myplant/modify")
    public ResponseEntity<SuccessResponse> modifyMyPlant(@RequestBody @Valid MyPlantPatchRequestDto myPlantPatchRequestDto) {
        plantRegisterService.patch(myPlantPatchRequestDto);
        return responseService.getSuccessResult(SuccessCode.PLANT_PATCH_SUCCESS);
    }

    @ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "내 식물로 삭제", notes = "토큰 인증 필요")
    @DeleteMapping("/myplant/delete/{myPlantId}")
    public ResponseEntity<SuccessResponse> deleteMyPlant(@PathVariable("myPlantId") Integer myPlantId) {
        plantRegisterService.delete(myPlantId);
        return responseService.getSuccessResult(SuccessCode.PLANT_DELETE_SUCCESS);
    }
}
