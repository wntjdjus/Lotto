package com.jutudy.lottoproject.winLotto;

import com.jutudy.lottoproject.winLotto.domain.WinLotto;
import com.jutudy.lottoproject.winLotto.domain.WinLottoRepository;
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
public class WinLottoRepositoryTests {

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
    public void 조회_회차() {
        WinLotto winLotto = winLottoRepository.findByRound(1L);
        assertThat(winLotto.getRound()).isEqualTo(1L);

        winLotto = winLottoRepository.findByRound(2L);
        assertThat(winLotto).isNull();
    }

    @Test
    public void 조회_가장최근() {
        winLottoRepository.save(
                WinLotto.builder()
                        .date("20211206")
                        .round(2L)
                        .firstWinnerCnt(1L)
                        .firstReward(1000L)
                        .firstTotalReward(1000L)
                        .num1(10L)
                        .num2(1L)
                        .num3(2L)
                        .num4(4L)
                        .num5(5L)
                        .num6(6L)
                        .bonusNum(7L)
                        .build()
        );

        WinLotto winLotto = winLottoRepository.findTopByOrderByRoundDesc();

        assertThat(winLotto.getRound()).isEqualTo(2L);
    }

}
