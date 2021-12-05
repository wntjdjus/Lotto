package com.jutudy.lottoproject.winLotto.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class WinLotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5, nullable = false)
    private Long round;

    @Column(length = 8, nullable = false)
    private String date;

    @Column(length = 2, nullable = false)
    private Long num1;

    @Column(length = 2, nullable = false)
    private Long num2;

    @Column(length = 2, nullable = false)
    private Long num3;

    @Column(length = 2, nullable = false)
    private Long num4;

    @Column(length = 2, nullable = false)
    private Long num5;

    @Column(length = 2, nullable = false)
    private Long num6;

    @Column(length = 2, nullable = false)
    private Long bonusNum;

    @Column(length = 20, nullable = false)
    private Long firstReward;

    @Column(length = 10, nullable = false)
    private Long firstWinnerCnt;

    @Column(length = 20, nullable = false)
    private Long firstTotalReward;

    @Builder
    public WinLotto(Long round, String date, Long num1, Long num2, Long num3, Long num4, Long num5, Long num6, Long bonusNum, Long firstReward, Long firstWinnerCnt, Long firstTotalReward) {
        this.round = round;
        this.date = date;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
        this.num6 = num6;
        this.bonusNum = bonusNum;
        this.firstReward = firstReward;
        this.firstWinnerCnt = firstWinnerCnt;
        this.firstTotalReward = firstTotalReward;
    }
}
