package com.jutudy.lottoproject.lotto.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Getter
@NoArgsConstructor
public class Lotto {

    private int round;
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private int num6;
    private int bonusNum;

    @Builder
    public Lotto(int round, int num1, int num2, int num3, int num4, int num5, int num6, int bonusNum) {
        int[] nums = this.sort(num1, num2, num3, num4, num5, num6);
        this.round = round;
        this.num1 = nums[0];
        this.num2 = nums[1];
        this.num3 = nums[2];
        this.num4 = nums[3];
        this.num5 = nums[4];
        this.num6 = nums[5];
        this.bonusNum = bonusNum;
    }

    private int[] sort(int num1, int num2, int num3, int num4, int num5, int num6) {
        int[] nums = {num1, num2, num3, num4, num5, num6};
        Arrays.sort(nums);

        return nums;
    }

    public void randomize() {
        Set<Integer> set = new HashSet<>();
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        while (list.size() < 6) {
            int num = random.nextInt(45) + 1;
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
