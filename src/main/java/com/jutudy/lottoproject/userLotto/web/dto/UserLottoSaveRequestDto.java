package com.jutudy.lottoproject.userLotto.web.dto;

import com.jutudy.lottoproject.userLotto.domain.UserLotto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLottoSaveRequestDto {

    private String userId;
    private long round;
    private long num1;
    private long num2;
    private long num3;
    private long num4;
    private long num5;
    private long num6;

    @Builder
    public UserLottoSaveRequestDto(String userId, long round, long num1, long num2, long num3, long num4, long num5, long num6) {
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
                .build();
    }
}
