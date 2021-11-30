package com.jutudy.lottoproject.userlotto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;

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
        int[] nums = this.sort(num1, num2, num3, num4, num5, num6);
        this.userId = userId;
        this.round = round;
        this.num1 = nums[0];
        this.num2 = nums[1];
        this.num3 = nums[2];
        this.num4 = nums[3];
        this.num5 = nums[4];
        this.num6 = nums[5];
        this.buyYn = buyYn;
    }

    public void update(int num1, int num2, int num3, int num4, int num5, int num6, String buyYn, String hitYn, int rank) {
        int[] nums = this.sort(num1, num2, num3, num4, num5, num6);
        this.num1 = nums[0];
        this.num2 = nums[1];
        this.num3 = nums[2];
        this.num4 = nums[3];
        this.num5 = nums[4];
        this.num6 = nums[5];
        this.buyYn = buyYn;
        this.hitYn = hitYn;
        this.rank = rank;
    }

    private int[] sort(int num1, int num2, int num3, int num4, int num5, int num6) {
        int[] nums = {num1, num2, num3, num4, num5, num6};
        Arrays.sort(nums);

        return nums;
    }
}
