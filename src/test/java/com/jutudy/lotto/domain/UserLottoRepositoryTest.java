package com.jutudy.lotto.domain;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserLottoRepositoryTest {

    @Autowired
    UserLottoRepository userLottoRepository;

    @After
    public void cleanup(){
        userLottoRepository.deleteAll();
    }

    @Test
    public void 유저로또저장_불러오기(){
        String userId = "test";
        int round = 1;
        int[] nums = {1,2,3,4,5,6};

        userLottoRepository.save(UserLotto.builder()
                .userId(userId)
                .round(round)
                .num1(nums[0])
                .num2(nums[1])
                .num3(nums[2])
                .num4(nums[3])
                .num5(nums[4])
                .num6(nums[5])
                .buyYn("N")
                .build());

        List<UserLotto> userLottoList = userLottoRepository.findAll();

        UserLotto userLotto = userLottoList.get(0);
        assertThat(userLotto.getUserId()).isEqualTo(userId);
        assertThat(userLotto.getRound()).isEqualTo(round);
        assertThat(userLotto.getNum1()).isEqualTo(nums[0]);
        assertThat(userLotto.getNum2()).isEqualTo(nums[1]);
        assertThat(userLotto.getNum3()).isEqualTo(nums[2]);
        assertThat(userLotto.getNum4()).isEqualTo(nums[3]);
        assertThat(userLotto.getNum5()).isEqualTo(nums[4]);
        assertThat(userLotto.getNum6()).isEqualTo(nums[5]);
        assertThat(userLotto.getBuyYn()).isEqualTo("N");
    }
}