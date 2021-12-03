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
    private int round;

    @Column(length = 8, nullable = false)
    private String date;

    @Column(length = 2, nullable = false)
    private int num1;

    @Column(length = 2, nullable = false)
    private int num2;

    @Column(length = 2, nullable = false)
    private int num3;

    @Column(length = 2, nullable = false)
    private int num4;

    @Column(length = 2, nullable = false)
    private int num5;

    @Column(length = 2, nullable = false)
    private int num6;

    @Column(length = 2, nullable = false)
    private int bonusNum;

    @Column(length = 20, nullable = false)
    private long firstReward;

    @Column(length = 10, nullable = false)
    private int firstWinnerCnt;

    @Column(length = 20, nullable = false)
    private long firstTotalReward;

    @Builder
    public WinLotto(Long id, int round, String date, int num1, int num2, int num3, int num4, int num5, int num6, int bonusNum, long firstReward, int firstWinnerCnt, long firstTotalReward) {
        this.id = id;
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
