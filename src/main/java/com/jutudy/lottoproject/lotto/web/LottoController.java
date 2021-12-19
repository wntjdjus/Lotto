package com.jutudy.lottoproject.lotto.web;

import com.jutudy.lottoproject.lotto.service.LottoService;
import com.jutudy.lottoproject.lotto.web.dto.RandomLottoResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@RequiredArgsConstructor
@RestController
public class LottoController {

    private static final Logger logger = LoggerFactory.getLogger(LottoController.class);
    private final LottoService lottoService;

    @GetMapping("/random-lotto")
    public RandomLottoResponseDto getRandomLotto(
            @RequestParam(value = "except-recent-round-cnt", required = false) String exceptRecentRoundCnt,
            @RequestParam(value = "except-nums", required = false) String exceptNums) {
        List<Long> exceptNumList = null;
        if (exceptNums != null) {
            exceptNumList = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(exceptNums, "+");
            while (st.hasMoreTokens()) {
                try {
                    exceptNumList.add(Long.parseLong(st.nextToken()));
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
        }
        RandomLottoResponseDto responseDto = lottoService.getRandomLotto(exceptRecentRoundCnt, exceptNumList);
        logger.debug(responseDto.toString());
        return responseDto;
    }

    @GetMapping("/test")
    public void error() {
    }
}
