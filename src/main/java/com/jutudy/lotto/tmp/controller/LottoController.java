package com.jutudy.lotto.tmp.controller;

import com.jutudy.lotto.userlotto.domain.UserLotto;
import com.jutudy.lotto.tmp.service.RandomLottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LottoController {

    private final RandomLottoService lottoService;

    @GetMapping("/random")
    public ResponseEntity<UserLotto> getRandomLotto(){
        //UserLotto randomLotto = lottoService.makeRandomLotto();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
