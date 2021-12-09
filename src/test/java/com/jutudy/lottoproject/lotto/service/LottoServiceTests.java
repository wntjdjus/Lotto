package com.jutudy.lottoproject.lotto.service;

import com.jutudy.lottoproject.lotto.domain.Lotto;
import com.jutudy.lottoproject.lotto.web.dto.RandomLottoResponseDto;
import com.jutudy.lottoproject.winLotto.domain.WinLotto;
import com.jutudy.lottoproject.winLotto.domain.WinLottoRepository;
import com.jutudy.lottoproject.winLotto.service.WinLottoService;
import com.jutudy.lottoproject.winLotto.web.dto.WinLottoResponseDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LottoServiceTests {

    @Autowired
    LottoService lottoService;

    @Autowired
    WinLottoRepository winLottoRepository;

    @Before
    public void makeWinLotto() {
        winLottoRepository.save(WinLotto.builder()
                .date("20211205")
                .firstReward(1000L)
                .firstTotalReward(2000L)
                .firstWinnerCnt(2L)
                .round(1L)
                .num1(1L)
                .num2(2L)
                .num3(3L)
                .num4(4L)
                .num5(5L)
                .num6(6L)
                .bonusNum(7L)
                .build());
    }

    @After
    public void cleanup() {
        winLottoRepository.deleteAll();
    }

    @Test
    public void 과거당첨내역조회() {
        Lotto lotto = Lotto.builder()
                .num1(1)
                .num2(2)
                .num3(3)
                .num4(4)
                .num5(5)
                .num6(6)
                .build();
        RandomLottoResponseDto dto = new RandomLottoResponseDto(lotto);
        dto = lottoService.findPastWinCnt(dto);

        assertThat(dto.getPastWin1Cnt()).isEqualTo(1);
        assertThat(dto.getPastWin2Cnt()).isEqualTo(0);
        assertThat(dto.getPastWin3Cnt()).isEqualTo(0);
        assertThat(dto.getPastWin4Cnt()).isEqualTo(0);
        assertThat(dto.getPastWin5Cnt()).isEqualTo(0);
    }

}
