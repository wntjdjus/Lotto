package com.jutudy.lottoproject.lotto.web;

import com.jutudy.lottoproject.lotto.service.LottoService;
import com.jutudy.lottoproject.lotto.web.dto.RandomLottoResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LottoController {

    private static final Logger logger = LoggerFactory.getLogger(LottoController.class);
    private final LottoService lottoService;

    @GetMapping("/random-lotto")
    public RandomLottoResponseDto getRandomLotto() {
        RandomLottoResponseDto responseDto = lottoService.getRandomLotto();
        logger.debug(responseDto.toString());
        return responseDto;
    }

    @GetMapping("/test")
    public void error() {
    }
}
