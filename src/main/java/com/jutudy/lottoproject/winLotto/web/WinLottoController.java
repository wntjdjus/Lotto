package com.jutudy.lottoproject.winLotto.web;

import com.jutudy.lottoproject.winLotto.service.WinLottoService;
import com.jutudy.lottoproject.winLotto.web.dto.WinLottoResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class WinLottoController {

    private static final Logger logger = LoggerFactory.getLogger(WinLottoController.class);
    private final WinLottoService winLottoService;

    @GetMapping("/win-lotto/recent")
    public WinLottoResponseDto getRecentWinLotto() {
        return winLottoService.selectRecentWinLotto();
    }
}
