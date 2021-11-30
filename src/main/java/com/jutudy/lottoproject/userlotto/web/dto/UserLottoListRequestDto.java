package com.jutudy.lottoproject.userlotto.web.dto;

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
