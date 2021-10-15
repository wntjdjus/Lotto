package com.jutudy.lotto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/lotto")
    public String index(){
        return "index1.html";
    }
}
