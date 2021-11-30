package com.jutudy.lottoproject.lotto.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {

    @Test
    public void 랜덤(){
        //given
        Lotto lotto = Lotto.builder().build();

        //when
        lotto.randomize();

        //then
        assertThat(lotto.getNum1()).isBetween(1,45);
        assertThat(lotto.getNum2()).isBetween(1,45);
        assertThat(lotto.getNum3()).isBetween(1,45);
        assertThat(lotto.getNum4()).isBetween(1,45);
        assertThat(lotto.getNum5()).isBetween(1,45);
        assertThat(lotto.getNum6()).isBetween(1,45);

        assertThat(lotto.getNum1()).isLessThan(lotto.getNum2());
        assertThat(lotto.getNum2()).isLessThan(lotto.getNum3());
        assertThat(lotto.getNum3()).isLessThan(lotto.getNum4());
        assertThat(lotto.getNum4()).isLessThan(lotto.getNum5());
        assertThat(lotto.getNum5()).isLessThan(lotto.getNum6());
    }
}
