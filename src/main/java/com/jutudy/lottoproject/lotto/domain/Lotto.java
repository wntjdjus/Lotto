package com.jutudy.lottoproject.lotto.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Getter
@NoArgsConstructor
public class Lotto {

    private long round;
    private long num1;
    private long num2;
    private long num3;
    private long num4;
    private long num5;
    private long num6;
    private long bonusNum;

    @Builder
    public Lotto(long round, long num1, long num2, long num3, long num4, long num5, long num6, long bonusNum) {
        long[] nums = this.sort(num1, num2, num3, num4, num5, num6);
        this.round = round;
        this.num1 = nums[0];
        this.num2 = nums[1];
        this.num3 = nums[2];
        this.num4 = nums[3];
        this.num5 = nums[4];
        this.num6 = nums[5];
        this.bonusNum = bonusNum;
    }

    private long[] sort(long num1, long num2, long num3, long num4, long num5, long num6) {
        long[] nums = {num1, num2, num3, num4, num5, num6};
        Arrays.sort(nums);

        return nums;
    }

    public void randomize() {
        Set<Long> set = new HashSet<>();
        Random random = new Random();
        List<Long> list = new ArrayList<>();
        while (list.size() < 6) {
            long num = random.nextInt(45) + 1;
            if (set.add(num)) {
                list.add(num);
            }
        }
        Collections.sort(list);

        this.num1 = list.get(0);
        this.num2 = list.get(1);
        this.num3 = list.get(2);
        this.num4 = list.get(3);
        this.num5 = list.get(4);
        this.num6 = list.get(5);
    }
}
