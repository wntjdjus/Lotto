package com.jutudy.lottoproject.lotto.web.dto;

import com.jutudy.lottoproject.lotto.domain.Lotto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RandomLottoResponseDto {

    private long num1;
    private long num2;
    private long num3;
    private long num4;
    private long num5;
    private long num6;
    private int pastWin1Cnt;
    private int pastWin2Cnt;
    private int pastWin3Cnt;
    private int pastWin4Cnt;
    private int pastWin5Cnt;

    public RandomLottoResponseDto(Lotto entity) {
        this.num1 = entity.getNum1();
        this.num2 = entity.getNum2();
        this.num3 = entity.getNum3();
        this.num4 = entity.getNum4();
        this.num5 = entity.getNum5();
        this.num6 = entity.getNum6();
        this.pastWin1Cnt = 0;
        this.pastWin2Cnt = 0;
        this.pastWin3Cnt = 0;
        this.pastWin4Cnt = 0;
        this.pastWin5Cnt = 0;
    }

    public void addPastWin1Cnt() {
        this.pastWin1Cnt += 1;
    }

    public void addPastWin2Cnt() {
        this.pastWin2Cnt += 1;
    }

    public void addPastWin3Cnt() {
        this.pastWin3Cnt += 1;
    }

    public void addPastWin4Cnt() {
        this.pastWin4Cnt += 1;
    }

    public void addPastWin5Cnt() {
        this.pastWin5Cnt += 1;
    }
}
