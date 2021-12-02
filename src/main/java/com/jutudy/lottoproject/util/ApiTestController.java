package com.jutudy.lottoproject.util;

import com.jutudy.lottoproject.util.ApiCallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
