package com.jutudy.lottoproject.winLotto.domain;

import com.jutudy.lottoproject.userLotto.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
public class WinLotto{

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

}
