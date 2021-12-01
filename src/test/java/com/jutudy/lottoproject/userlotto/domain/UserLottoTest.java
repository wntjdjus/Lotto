package com.jutudy.lottoproject.userlotto.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserLottoTest {

    @Test
    public void 정렬(){
        //given
        UserLotto userLotto = UserLotto.builder()
                .userId("TEST")
                .round(1)
                .num1(30)
                .num2(25)
                .num3(45)
                .num4(1)
                .num5(14)
                .num6(44)
                .buyYn("N")
                .build();

        assertThat(userLotto.getNum1()).isLessThan(userLotto.getNum2());
        assertThat(userLotto.getNum2()).isLessThan(userLotto.getNum3());
        assertThat(userLotto.getNum3()).isLessThan(userLotto.getNum4());
        assertThat(userLotto.getNum4()).isLessThan(userLotto.getNum5());
        assertThat(userLotto.getNum5()).isLessThan(userLotto.getNum6());
    }
}
