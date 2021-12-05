package com.jutudy.lottoproject.winLotto.web.dto;

import com.jutudy.lottoproject.winLotto.domain.WinLotto;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class WinLottoResponseDto {

    private Long id;
    private Long round;
    private String date;
    private Long num1;
    private Long num2;
    private Long num3;
    private Long num4;
    private Long num5;
    private Long num6;
    private Long bonusNum;
    private Long firstReward;
    private Long firstWinnerCnt;
    private Long firstTotalReward;

    public WinLottoResponseDto(WinLotto entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
