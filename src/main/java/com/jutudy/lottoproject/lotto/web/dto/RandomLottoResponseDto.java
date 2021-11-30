package com.jutudy.lottoproject.lotto.web.dto;

import com.jutudy.lottoproject.lotto.domain.Lotto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RandomLottoResponseDto {

    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private int num6;

    public RandomLottoResponseDto(Lotto entity) {
        this.num1 = entity.getNum1();
        this.num2 = entity.getNum2();
        this.num3 = entity.getNum3();
        this.num4 = entity.getNum4();
        this.num5 = entity.getNum5();
        this.num6 = entity.getNum6();
    }
}
