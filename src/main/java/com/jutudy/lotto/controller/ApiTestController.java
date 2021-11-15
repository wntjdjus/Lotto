package com.jutudy.lotto.controller;

import com.jutudy.lotto.service.ApiCallService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ApiTestController {

    private final ApiCallService apiCallService;

    @GetMapping("/api")
    public ResponseEntity callApi(){

        apiCallService.solve();

        return new ResponseEntity(HttpStatus.OK);
    }
}
