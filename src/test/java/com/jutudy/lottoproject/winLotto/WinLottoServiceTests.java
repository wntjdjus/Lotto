package com.jutudy.lottoproject.winLotto;

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

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WinLottoServiceTests {

    @Autowired
    WinLottoService winLottoService;

    @Autowired
    WinLottoRepository winLottoRepository;

    @Before
    public void makeWinLotto() {
    }

    @After
    public void cleanup() {
        winLottoRepository.deleteAll();
    }

    @Test
    public void 조회_회차() {
        //given
        Long round = 1L;

        //when
        WinLottoResponseDto responseDto = winLottoService.selectWinLottoByRound(round);

        //then
        assertThat(responseDto.getRound()).isEqualTo(round);
        assertThat(responseDto.getDate()).isEqualTo("20021207");
        assertThat(responseDto.getNum1()).isEqualTo(10);
        assertThat(responseDto.getNum2()).isEqualTo(23);
        assertThat(responseDto.getNum3()).isEqualTo(29);
        assertThat(responseDto.getNum4()).isEqualTo(33);
        assertThat(responseDto.getNum5()).isEqualTo(37);
        assertThat(responseDto.getNum6()).isEqualTo(40);
        assertThat(responseDto.getBonusNum()).isEqualTo(16);
        assertThat(responseDto.getFirstReward()).isEqualTo(0);
        assertThat(responseDto.getFirstWinnerCnt()).isEqualTo(0);
        assertThat(responseDto.getFirstTotalReward()).isEqualTo(863604600);

    }

    @Test(expected = RuntimeException.class)
    public void 조회_회차_실패(){
        Long round = 9999L;
        WinLottoResponseDto responseDto = winLottoService.selectWinLottoByRound(round);
    }

}
