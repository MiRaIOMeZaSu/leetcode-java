package leetcode.editor.contest._5979_earliestFullBloom;

import java.util.Arrays;
import java.util.Comparator;

class Plant {
    Plant(int p, int g) {
        plantTime = p;
        growTime = g;
        total = p + g;
    }

    int plantTime;
    int growTime;
    int total;
}

class Solution {
    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        int size = plantTime.length;
        Plant[] plants = new Plant[size];
        for (int i = 0; i < size; i++) {
            plants[i] = new Plant(plantTime[i], growTime[i] + 1);
        }
        Arrays.sort(plants, (o1, o2) -> {
            int a1 = Math.max(o1.total, o2.total + o1.plantTime);
            int a2 = Math.max(o2.total, o1.total + o2.plantTime);
            return a1 - a2;
        });
        int ans = Integer.MIN_VALUE;
        int curr = 0;
        for (int i = 0; i < size; i++) {
            ans = Math.max(ans, curr + plants[i].total);
            curr += plants[i].plantTime;
        }
        return ans - 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.earliestFullBloom(
                new int[]{1, 4, 3},
                new int[]{2, 3, 1}
        );
    }
}