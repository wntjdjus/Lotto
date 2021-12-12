package com.jutudy.lottoproject.userLotto.web.dto;

import com.jutudy.lottoproject.userLotto.domain.UserLotto;
import com.jutudy.lottoproject.winLotto.domain.WinLotto;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class UserLottoResponseDto {

    private Long id;
    private String userId;
    private long round;
    private long num1;
    private long num2;
    private long num3;
    private long num4;
    private long num5;
    private long num6;
    private String buyYn;
    private long rank;

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
    }

    public void findRank(WinLotto winLotto) {
        int hitCnt = 0;
        Set winSet = new HashSet();
        winSet.add(winLotto.getNum1());
        winSet.add(winLotto.getNum2());
        winSet.add(winLotto.getNum3());
        winSet.add(winLotto.getNum4());
        winSet.add(winLotto.getNum5());
        winSet.add(winLotto.getNum6());

        if (winSet.contains(this.num1)) {
            hitCnt += 1;
        }
        if (winSet.contains(this.num2)) {
            hitCnt += 1;
        }
        if (winSet.contains(this.num3)) {
            hitCnt += 1;
        }
        if (winSet.contains(this.num4)) {
            hitCnt += 1;
        }
        if (winSet.contains(this.num5)) {
            hitCnt += 1;
        }
        if (winSet.contains(this.num6)) {
            hitCnt += 1;
        }

        switch (hitCnt) {
            case 6:
                this.rank = 1;
                break;
            case 5:
                if (winLotto.getBonusNum() == this.num1
                        || winLotto.getBonusNum() == this.num2
                        || winLotto.getBonusNum() == this.num3
                        || winLotto.getBonusNum() == this.num4
                        || winLotto.getBonusNum() == this.num5
                        || winLotto.getBonusNum() == this.num6) {
                    this.rank = 2;
                } else {
                    this.rank = 3;
                }
                break;
            case 4:
                this.rank = 4;
                break;
            case 3:
                this.rank = 5;
                break;
            default:
                break;
        }
    }

}
