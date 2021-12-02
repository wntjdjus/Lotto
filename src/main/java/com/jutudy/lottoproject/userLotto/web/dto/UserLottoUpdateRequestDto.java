package com.jutudy.lottoproject.userLotto.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLottoUpdateRequestDto {

    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private int num6;
    private String buyYn;

    @Builder
    public UserLottoUpdateRequestDto(int num1, int num2, int num3, int num4, int num5, int num6, String buyYn) {
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
        this.num6 = num6;
        this.buyYn = buyYn;
    }
}
