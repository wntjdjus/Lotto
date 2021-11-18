package com.jutudy.lotto.userlotto.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class UserLotto extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String userId;

    @Column(length = 5, nullable = false)
    private int round;

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

    @Column(length = 1, nullable = false)
    private String buyYn;

    @Column(length = 1)
    private String hitYn;

    @Column(length = 1)
    private int rank;

    @Builder
    public UserLotto(String userId, int round, int num1, int num2, int num3, int num4, int num5, int num6, String buyYn) {
        this.userId = userId;
        this.round = round;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
        this.num6 = num6;
        this.buyYn = buyYn;
    }

    public void update(int num1, int num2, int num3, int num4, int num5, int num6, String buyYn, String hitYn, int rank) {
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
        this.num6 = num6;
        this.buyYn = buyYn;
        this.hitYn = hitYn;
        this.rank = rank;
    }
}
