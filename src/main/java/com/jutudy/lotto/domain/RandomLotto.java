package com.jutudy.lotto.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RandomLotto{

    private String userId;
    private String date;
    private int no;
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private int num6;
    private int extraNum;
    private String buyYn;
    private String hitYn;
    private int rank;
    private LocalDateTime fstRgTs;
    private LocalDateTime ltChTs;

    public RandomLotto(String userId, String date, int num1, int num2, int num3, int num4, int num5, int num6, int extraNum) {
        this.userId = userId;
        this.date = date;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
        this.num6 = num6;
        this.extraNum = extraNum;
    }
}
