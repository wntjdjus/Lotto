package com.jutudy.lottoproject.userLotto.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserLottoRepositoryTest {

    @Autowired
    UserLottoRepository userLottoRepository;

    @Before
    public void makeUserLotto(){
        userLottoRepository.save(UserLotto.builder()
                .userId("test")
                .round(1)
                .num1(1)
                .num2(2)
                .num3(3)
                .num4(4)
                .num5(5)
                .num6(6)
                .build());
    }

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

    @Test
    public void BaseTimeEntity_등록(){

        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        userLottoRepository.save(
                UserLotto.builder()
                        .userId("test")
                        .round(1)
                        .num1(1)
                        .num2(2)
                        .num3(3)
                        .num4(4)
                        .num5(5)
                        .num6(6)
                        .build()
        );

        //when
        List<UserLotto> userLottoList = userLottoRepository.findAll();

        //then
        UserLotto userLotto = userLottoList.get(0);

        System.out.println(">>>>>>>> createdDate : "+userLotto.getCreatedDate()+
                ", modifiedDate : "+userLotto.getModifiedDate());

        assertThat(userLotto.getCreatedDate()).isAfter(now);
        assertThat(userLotto.getModifiedDate()).isAfter(now);
    }

    @Test
    public void 회차별저장내역조회(){
        //given
        UserLotto userLotto = UserLotto.builder()
                .userId("test")
                .round(1)
                .num1(1)
                .num2(2)
                .num3(3)
                .num4(4)
                .num5(5)
                .num6(6)
                .build();
        userLottoRepository.save(userLotto);

        //when
        List<UserLotto> lottos = userLottoRepository.findAllByUserIdAndRoundOrderByCreatedDate("test",1);

        //then
        assertThat(lottos.get(0).getUserId()).isEqualTo("test");
        assertThat(lottos.get(0).getRound()).isEqualTo(1);

        //when
        lottos = userLottoRepository.findAllByUserIdAndRoundOrderByCreatedDate("test",2);

        //then
        assertThat(lottos).isEmpty();
    }

    @Test
    public void 라운드별목록조회(){
        List<UserLotto> userLotto = userLottoRepository.findAllByUserIdAndRoundOrderByCreatedDate("test",1);

        assertThat(userLotto.get(0).getUserId()).isEqualTo("test");
        assertThat(userLotto.get(0).getRound()).isEqualTo(1);

        userLotto = userLottoRepository.findAllByUserIdAndRoundOrderByCreatedDate("test",2);

        assertThat(userLotto).isEmpty();
    }
}
