package com.jutudy.lotto.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RandomLottoResponseDto {

    private int lottoNum1;
    private int lottoNum2;
    private int lottoNum3;
    private int lottoNum4;
    private int lottoNum5;
    private int lottoNum6;

    public RandomLottoResponseDto(int lottoNum1, int lottoNum2, int lottoNum3, int lottoNum4, int lottoNum5, int lottoNum6) {
        this.lottoNum1 = lottoNum1;
        this.lottoNum2 = lottoNum2;
        this.lottoNum3 = lottoNum3;
        this.lottoNum4 = lottoNum4;
        this.lottoNum5 = lottoNum5;
        this.lottoNum6 = lottoNum6;
    }
}
