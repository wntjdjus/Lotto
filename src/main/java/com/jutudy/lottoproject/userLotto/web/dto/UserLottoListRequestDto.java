package com.jutudy.lottoproject.userLotto.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLottoListRequestDto {

    private String userId;
    private long round;

    @Builder
    public UserLottoListRequestDto(String userId, long round) {
        this.userId = userId;
        this.round = round;
    }
}
