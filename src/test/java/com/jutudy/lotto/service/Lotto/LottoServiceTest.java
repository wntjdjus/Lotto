package com.jutudy.lotto.service.Lotto;

import com.jutudy.lotto.domain.Lotto.Lotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
public class LottoServiceTest {
    private LottoService lottoService;
    private Lotto lotto;
    private Set<Integer> lottoSet = new HashSet<>();

    @BeforeEach
    public void setLottoService(){
        lottoService = new LottoService();
    }

    @Test
    public void getLandomLottoTest() {
        lotto = lottoService.getRandomLotto();

        for(int i = 0; i<=10000; i++) {

            lottoSet = new HashSet<>();
            assertThat(lotto.getNum1() >= 1 && lotto.getNum1() <= 45).isTrue();
            assertThat(lotto.getNum2() >= 1 && lotto.getNum2() <= 45).isTrue();
            assertThat(lotto.getNum3() >= 1 && lotto.getNum3() <= 45).isTrue();
            assertThat(lotto.getNum4() >= 1 && lotto.getNum4() <= 45).isTrue();
            assertThat(lotto.getNum5() >= 1 && lotto.getNum5() <= 45).isTrue();
            assertThat(lotto.getNum6() >= 1 && lotto.getNum6() <= 45).isTrue();
            assertThat(lotto.getExtraNum() >= 1 && lotto.getExtraNum() <= 45).isTrue();

            assertThat(lottoSet).doesNotContain(lotto.getNum1());
            lottoSet.add(lotto.getNum1());
            assertThat(lottoSet).doesNotContain(lotto.getNum2());
            lottoSet.add(lotto.getNum2());
            assertThat(lottoSet).doesNotContain(lotto.getNum3());
            lottoSet.add(lotto.getNum3());
            assertThat(lottoSet).doesNotContain(lotto.getNum4());
            lottoSet.add(lotto.getNum4());
            assertThat(lottoSet).doesNotContain(lotto.getNum5());
            lottoSet.add(lotto.getNum5());
            assertThat(lottoSet).doesNotContain(lotto.getNum6());
            lottoSet.add(lotto.getNum6());
            assertThat(lottoSet).doesNotContain(lotto.getExtraNum());
            lottoSet.add(lotto.getExtraNum());
        }
    }
}
