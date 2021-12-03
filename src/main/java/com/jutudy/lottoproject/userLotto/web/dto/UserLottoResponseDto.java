package com.jutudy.lottoproject.userLotto.web.dto;

import com.jutudy.lottoproject.userLotto.domain.UserLotto;
import lombok.Getter;

@Getter
public class UserLottoResponseDto {

    private Long id;
    private String userId;
    private int round;
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private int num6;
    private String buyYn;
    private int rank;

    public UserLottoResponseDto(UserLotto entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.round = entity.getRound();
        this.num1 = entity.getNum1();
        this.num2 = entity.getNum2();
        this.num3 = entity.getNum3();
        this.num4 = entity.getNum4();
        this.num5 = entity.getNum5();
        this.num6 = entity.getNum6();
        this.buyYn = entity.getBuyYn();
        //this.rank = entity.getRank();
    }
}
