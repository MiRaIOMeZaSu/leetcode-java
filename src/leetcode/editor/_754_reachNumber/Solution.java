package leetcode.editor._754_reachNumber;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class Solution {
    public int reachNumber(int target) {
        // 使用(哈希链表)背包法
        int level = (int) Math.sqrt(target);
        ArrayList<Integer> list = new ArrayList<>(level);
        HashSet<Integer> set = new HashSet<>(level);
        list.add(0);
        set.add(0);
        int index = 1;
        int currSum = 0;
        while (true) {
            currSum += index;
            int currSize = list.size();
            for (int i = 0; i < currSize; i++) {
                int num = list.get(i);
                if (2 * num - currSum == target) {
                    return index;
                }
                int next = num + index;
                if (!set.contains(next)) {
                    if (2 * next - currSum == target) {
                        return index;
                    }
                    set.add(next);
                    list.add(next);
                }
            }
            index++;
        }
    }
}