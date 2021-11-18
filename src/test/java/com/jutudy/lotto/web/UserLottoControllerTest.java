package com.jutudy.lotto.web;

import com.jutudy.lotto.userlotto.domain.UserLotto;
import com.jutudy.lotto.userlotto.domain.UserLottoRepository;
import com.jutudy.lotto.userlotto.web.dto.UserLottoSaveRequestDto;
import com.jutudy.lotto.userlotto.web.dto.UserLottoUpdateRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserLottoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserLottoRepository userLottoRepository;

    @Test
    public void UserLotto_등록() throws Exception{

        String userId = "test";
        int round = 1;
        int[] nums = {1,2,3,4,5,6};

        UserLottoSaveRequestDto requestDto = UserLottoSaveRequestDto.builder()
                .userId(userId)
                .round(round)
                .num1(nums[0])
                .num2(nums[1])
                .num3(nums[2])
                .num4(nums[3])
                .num5(nums[4])
                .num6(nums[5])
                .build();

        String url = "http://localhost:"+port+"/userlotto";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<UserLotto> all = userLottoRepository.findAll();
        assertThat(all.get(0).getUserId()).isEqualTo(userId);
        assertThat(all.get(0).getRound()).isEqualTo(round);
        assertThat(all.get(0).getNum1()).isEqualTo(nums[0]);
        assertThat(all.get(0).getNum2()).isEqualTo(nums[1]);
        assertThat(all.get(0).getNum3()).isEqualTo(nums[2]);
        assertThat(all.get(0).getNum4()).isEqualTo(nums[3]);
        assertThat(all.get(0).getNum5()).isEqualTo(nums[4]);
        assertThat(all.get(0).getNum6()).isEqualTo(nums[5]);
    }

    @Test
    public void UserLotto_수정() throws Exception{

        String userId = "test";
        int round = 1;
        int[] nums = {1,2,3,4,5,6};

        UserLotto userLotto = userLottoRepository.save(UserLotto.builder()
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

        Long id = userLotto.getId();
        int num1 = 45;
        int num2 = 44;
        int num3 = 43;
        int num4 = 42;
        int num5 = 41;
        int num6 = 40;
        String buyYn = "Y";
        String hitYn = "N";

        UserLottoUpdateRequestDto requestDto = UserLottoUpdateRequestDto.builder()
                .num1(num1)
                .num2(num2)
                .num3(num3)
                .num4(num4)
                .num5(num5)
                .num6(num6)
                .buyYn(buyYn)
                .hitYn(hitYn)
                .build();

        String url = "http://localhost:"+port+"/userlotto/"+id;

        HttpEntity<UserLottoUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<UserLotto> all = userLottoRepository.findAll();
        assertThat(all.get(0).getUserId()).isEqualTo(userId);
        assertThat(all.get(0).getRound()).isEqualTo(round);
        assertThat(all.get(0).getNum1()).isEqualTo(num1);
        assertThat(all.get(0).getNum2()).isEqualTo(num2);
        assertThat(all.get(0).getNum3()).isEqualTo(num3);
        assertThat(all.get(0).getNum4()).isEqualTo(num4);
        assertThat(all.get(0).getNum5()).isEqualTo(num5);
        assertThat(all.get(0).getNum6()).isEqualTo(num6);
        assertThat(all.get(0).getBuyYn()).isEqualTo(buyYn);
        assertThat(all.get(0).getHitYn()).isEqualTo(hitYn);
        //assertThat(all.get(0).getRank()).isNull();
    }
}
