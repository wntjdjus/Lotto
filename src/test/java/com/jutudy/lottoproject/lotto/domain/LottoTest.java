package com.jutudy.lottoproject.lotto.domain;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {

    @Test
    public void 랜덤(){
        //given
        Lotto lotto = Lotto.builder().build();

        //when
        lotto.randomize(null);

        //then
        assertThat(lotto.getNum1()).isBetween(1L,45L);
        assertThat(lotto.getNum2()).isBetween(1L,45L);
        assertThat(lotto.getNum3()).isBetween(1L,45L);
        assertThat(lotto.getNum4()).isBetween(1L,45L);
        assertThat(lotto.getNum5()).isBetween(1L,45L);
        assertThat(lotto.getNum6()).isBetween(1L,45L);

        assertThat(lotto.getNum1()).isLessThan(lotto.getNum2());
        assertThat(lotto.getNum2()).isLessThan(lotto.getNum3());
        assertThat(lotto.getNum3()).isLessThan(lotto.getNum4());
        assertThat(lotto.getNum4()).isLessThan(lotto.getNum5());
        assertThat(lotto.getNum5()).isLessThan(lotto.getNum6());
    }
    @Test
    public void 랜덤실패(){
        //given
        Set<Long> set = new HashSet<>();
        for(long i=1;i<=45;i++){
            set.add(i);
        }

        //when
        Lotto lotto = Lotto.builder().build().randomize(set);

        //then
        assertThat(lotto).isNull();
    }
}
