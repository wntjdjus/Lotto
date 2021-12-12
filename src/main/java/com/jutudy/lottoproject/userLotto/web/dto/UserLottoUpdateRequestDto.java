package com.jutudy.lottoproject.userLotto.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLottoUpdateRequestDto {

    private long num1;
    private long num2;
    private long num3;
    private long num4;
    private long num5;
    private long num6;
    private String buyYn;

    @Builder
    public UserLottoUpdateRequestDto(long num1, long num2, long num3, long num4, long num5, long num6, String buyYn) {
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
        this.num6 = num6;
        this.buyYn = buyYn;
    }
}
