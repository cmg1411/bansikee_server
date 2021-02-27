package com.tomasfriends.bansikee_server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Api(tags = {"1. User"})
@Controller
public class HelloController {

    @ApiOperation(value = "회원 조회", notes = "모든 회원 조회")
    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("data", "hello~!! 이제 된다 !! 진짜 된다 ! 슬렉 알림도 ! 데이터베이스 연동도 !");
        return "hello";
    }
}
