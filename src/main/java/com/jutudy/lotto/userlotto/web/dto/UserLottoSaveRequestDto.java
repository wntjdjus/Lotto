package com.jutudy.lotto.userlotto.web.dto;

import com.jutudy.lotto.userlotto.domain.UserLotto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLottoSaveRequestDto {

    private String userId;
    private int round;
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private int num6;

    @Builder
    public UserLottoSaveRequestDto(String userId, int round, int num1, int num2, int num3, int num4, int num5, int num6) {
        this.userId = userId;
        this.round = round;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
        this.num6 = num6;
    }

    public UserLotto toEntity(){
        return UserLotto.builder()
                .userId(userId)
                .round(round)
                .num1(num1)
                .num2(num2)
                .num3(num3)
                .num4(num4)
                .num5(num5)
                .num6(num6)
                .buyYn("N")
                .build();
    }
}
