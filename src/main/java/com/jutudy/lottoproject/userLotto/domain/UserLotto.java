package com.jutudy.lottoproject.userLotto.domain;

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
    private long round;

    @Column(length = 2, nullable = false)
    private long num1;

    @Column(length = 2, nullable = false)
    private long num2;

    @Column(length = 2, nullable = false)
    private long num3;

    @Column(length = 2, nullable = false)
    private long num4;

    @Column(length = 2, nullable = false)
    private long num5;

    @Column(length = 2, nullable = false)
    private long num6;

    @Column(length = 1, nullable = false)
    private String buyYn;

    @Builder
    public UserLotto(String userId, long round, long num1, long num2, long num3, long num4, long num5, long num6) {
        long[] nums = this.sort(num1, num2, num3, num4, num5, num6);
        this.userId = userId;
        this.round = round;
        this.num1 = nums[0];
        this.num2 = nums[1];
        this.num3 = nums[2];
        this.num4 = nums[3];
        this.num5 = nums[4];
        this.num6 = nums[5];
        this.buyYn = "N";
    }

    public UserLotto update(long num1, long num2, long num3, long num4, long num5, long num6, String buyYn) {
        long[] nums = this.sort(num1, num2, num3, num4, num5, num6);
        this.num1 = nums[0];
        this.num2 = nums[1];
        this.num3 = nums[2];
        this.num4 = nums[3];
        this.num5 = nums[4];
        this.num6 = nums[5];
        this.buyYn = buyYn;

        return this;
    }

    private long[] sort(long num1, long num2, long num3, long num4, long num5, long num6) {
        long[] nums = {num1, num2, num3, num4, num5, num6};
        Arrays.sort(nums);

        return nums;
    }
}
