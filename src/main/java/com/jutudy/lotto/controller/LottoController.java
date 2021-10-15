package com.jutudy.lotto.controller;

import com.jutudy.lotto.domain.Lotto;
import com.jutudy.lotto.service.LottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LottoController {

    private final LottoService lottoService;

    @GetMapping("/random")
    public ResponseEntity getLandomLotto(){
        Lotto randomLotto = lottoService.makeRandomLotto();
        return ResponseEntity.ok(randomLotto);
    }
}
