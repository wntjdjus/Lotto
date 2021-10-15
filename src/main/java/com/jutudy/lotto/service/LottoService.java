package com.jutudy.lotto.service;

import com.jutudy.lotto.domain.Lotto;
import com.jutudy.lotto.domain.RandomLottoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class LottoService {

    private final RandomLottoRepository randomLottoRepository;

    public Lotto makeRandomLotto() {

        Random random = new Random();
        boolean[] chk = new boolean[46];
        int[] lotto = new int[6];
        int extraNum = -1;

        for (int i = 0; i <= 6; i++) {
            while(true){
                int num = random.nextInt(44)+1;

                if(!chk[num]){
                    chk[num]=true;
                    if(i==6){
                        extraNum = num;
                    }else{
                        lotto[i] = num;
                    }
                    break;
                }
            }
        }
        Arrays.sort(lotto);

        return new Lotto(lotto[0],lotto[1],lotto[2],lotto[3],lotto[4],lotto[5],extraNum);
    }
}


