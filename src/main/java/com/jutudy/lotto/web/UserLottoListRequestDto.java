package com.jutudy.lotto.web;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLottoListRequestDto {

    private String userId;
    private int round;

    @Builder
    public UserLottoListRequestDto(String userId, int round) {
        this.userId = userId;
        this.round = round;
    }
}
