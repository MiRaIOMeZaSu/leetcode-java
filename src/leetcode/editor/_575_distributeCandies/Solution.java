package leetcode.editor._575_distributeCandies;

import java.util.HashSet;

class Solution {
    public int distributeCandies(int[] candyType) {
        // 小于等于数组大小的一半
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < candyType.length; i++) {
            set.add(candyType[i]);
        }
        return Math.min(candyType.length / 2, set.size());
    }
}