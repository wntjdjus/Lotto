package com.jutudy.lottoproject.winLotto.web.dto;

import lombok.Data;

@Data
public class WinLottoResponseDto {

    private Long id;
    private int round;
    private String date;
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private int num6;
    private int bonusNum;
    private long firstReward;
    private int firstWinnerCnt;
    private long firstTotalReward;
}
