package com.tomasfriends.bansikee_server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String hello(Model model) {
        model.addAttribute("data", "hello~!! 이제 된다 !! 진짜 된다 !");
        return "hello";
    }
}
