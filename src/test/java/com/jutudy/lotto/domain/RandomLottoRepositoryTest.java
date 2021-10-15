package com.jutudy.lotto.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource("classpath:application-test.properties")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RandomLottoRepositoryTest {

    @Autowired
    private RandomLottoRepository randomLottoRepository;

    @Test
    public void TEST_insert() {
        RandomLotto randomLotto = new RandomLotto(
                "testId", "20211002", 1, 2, 3, 4, 5, 6, 7);

        assertThat(randomLottoRepository.insert(randomLotto)).isEqualTo(1);

        List<RandomLotto> randomLottos = randomLottoRepository.selectAll(randomLotto);
        int listSize = randomLottos.size();

        assertThat(listSize).isEqualTo(1);
        assertThat(randomLottos.get(0).getUserId()).isEqualTo(randomLotto.getUserId());
        assertThat(randomLottos.get(0).getDate()).isEqualTo(randomLotto.getDate());
        assertThat(randomLottos.get(0).getNo()).isEqualTo(1);
    }

    @Test
    public void TEST_중복생성() {
        RandomLotto randomLotto = new RandomLotto(
                "test1", "20211010", 1, 2, 3, 4, 5, 6, 7);
        RandomLotto randomLotto1 = new RandomLotto(
                "test1", "20211010", 1, 2, 3, 4, 5, 6, 7);

        assertThat(randomLottoRepository.insert(randomLotto)).isEqualTo(1);
        assertThat(randomLottoRepository.insert(randomLotto1)).isEqualTo(1);

        List<RandomLotto> lottos = randomLottoRepository.selectAll(randomLotto);
        int ll = lottos.size();

        assertThat(ll).isEqualTo(2);
        for (int i = 0; i < ll; i++) {
            System.out.println(lottos.get(i).getUserId());
            assertThat(lottos.get(i).getUserId()).isEqualTo(randomLotto.getUserId()).isEqualTo(randomLotto1.getUserId());
            assertThat(lottos.get(i).getDate()).isEqualTo(randomLotto.getDate()).isEqualTo(randomLotto1.getDate());
            assertThat(lottos.get(i).getNo()).isEqualTo(i+1);
        }
    }

}
