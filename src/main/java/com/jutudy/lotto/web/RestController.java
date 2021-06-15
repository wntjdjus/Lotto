package com.jutudy.lotto.web;

import com.jutudy.lotto.service.Lotto.LottoService;
import com.jutudy.lotto.web.dto.RandomLottoResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@org.springframework.web.bind.annotation.RestController
public class RestController {

    private Logger logger = LoggerFactory.getLogger(RestController.class);
    private final LottoService lottoService;

    @GetMapping("/random-lotto")
    public RandomLottoResponseDto hello() {

        logger.info("########## /random-lotto 진입");

        RandomLottoResponseDto odto = lottoService.getRandomLotto();

        logger.info("########## /random-lotto 종료");

        return odto;
    }
}
