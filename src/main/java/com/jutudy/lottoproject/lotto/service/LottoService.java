package com.jutudy.lottoproject.lotto.service;

import com.jutudy.lottoproject.lotto.domain.Lotto;
import com.jutudy.lottoproject.lotto.web.dto.RandomLottoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LottoService {

    public RandomLottoResponseDto getRandomLotto(){
        RandomLottoResponseDto responseDto = new RandomLottoResponseDto();
        Lotto lotto = Lotto.builder().build();
        lotto.randomize();

        return new RandomLottoResponseDto(lotto);
    }
}


