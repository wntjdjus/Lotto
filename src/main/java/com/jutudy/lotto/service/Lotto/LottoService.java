package com.jutudy.lotto.service.Lotto;

import com.jutudy.lotto.web.dto.RandomLottoResponseDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class LottoService {

    public RandomLottoResponseDto getRandomLotto() {

        Random random = new Random();
        boolean[] chk = new boolean[46];
        int[] lotto = new int[6];

        for (int i = 0; i < 6; i++) {

            while(true){

                int num = random.nextInt(44)+1;

                if(!chk[num]){

                    chk[num]=true;
                    lotto[i] = num;
                    break;
                }
            }
        }
        Arrays.sort(lotto);

        return new RandomLottoResponseDto(lotto[0],lotto[1],lotto[2],lotto[3],lotto[4],lotto[5]);
    }
}
