package com.jutudy.lottoproject.winLotto;

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

    @Test
    public void 최근토요일까지저장(){
        Long round = 992L;
        int r = 992;
        String date = "20211204";
        winLottoService.insertWinLotto(1,date);

        WinLottoResponseDto dto = winLottoService.selectWinLottoByRound(round);

        List<WinLotto> list = winLottoRepository.findAll();

        assertThat(dto).isNotNull();
        assertThat(dto.getDate()).isEqualTo(date);
        assertThat(list.size()).isEqualTo(r);
    }

    @Test
    public void 최근로또조회(){
        int round = 992;
        String date = "20211204";
        WinLottoResponseDto dto = winLottoService.selectRecentWinLotto();
        assertThat(dto.getRound()).isEqualTo((long)round);
    }

}
