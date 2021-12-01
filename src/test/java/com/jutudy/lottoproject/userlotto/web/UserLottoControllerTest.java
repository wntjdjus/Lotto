package com.jutudy.lottoproject.userlotto.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jutudy.lottoproject.userlotto.domain.UserLotto;
import com.jutudy.lottoproject.userlotto.domain.UserLottoRepository;
import com.jutudy.lottoproject.userlotto.web.dto.UserLottoSaveRequestDto;
import com.jutudy.lottoproject.userlotto.web.dto.UserLottoUpdateRequestDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserLottoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserLottoRepository userLottoRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        userLottoRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "GUEST")
    public void UserLotto_등록() throws Exception{

        // given
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

        // when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        // then
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
    @WithMockUser(roles = "GUEST")
    public void UserLotto_수정() throws Exception{

        // given
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
        int[] afNums = {45,44,43,42,41,40};
        String buyYn = "Y";
        String hitYn = "N";

        UserLottoUpdateRequestDto requestDto = UserLottoUpdateRequestDto.builder()
                .num1(afNums[0])
                .num2(afNums[1])
                .num3(afNums[2])
                .num4(afNums[3])
                .num5(afNums[4])
                .num6(afNums[5])
                .buyYn(buyYn)
                .hitYn(hitYn)
                .build();

        String url = "http://localhost:"+port+"/userlotto/"+id;

        // when
        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        // then
        List<UserLotto> all = userLottoRepository.findAll();
        Arrays.sort(afNums);
        assertThat(all.get(0).getUserId()).isEqualTo(userId);
        assertThat(all.get(0).getRound()).isEqualTo(round);
        assertThat(all.get(0).getNum1()).isEqualTo(afNums[0]);
        assertThat(all.get(0).getNum2()).isEqualTo(afNums[1]);
        assertThat(all.get(0).getNum3()).isEqualTo(afNums[2]);
        assertThat(all.get(0).getNum4()).isEqualTo(afNums[3]);
        assertThat(all.get(0).getNum5()).isEqualTo(afNums[4]);
        assertThat(all.get(0).getNum6()).isEqualTo(afNums[5]);
        assertThat(all.get(0).getBuyYn()).isEqualTo(buyYn);
        assertThat(all.get(0).getHitYn()).isEqualTo(hitYn);
    }
}
