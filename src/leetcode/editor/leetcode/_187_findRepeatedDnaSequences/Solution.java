package leetcode.editor._187_findRepeatedDnaSequences;

import java.util.*;

class Solution {
    Map<Character, Integer> characterIntegerMap = new HashMap<>();
    char[] alp = new char[]{'A', 'C', 'G', 'T'};

    Solution() {
        for (int i = 0; i < 4; i++) {
            characterIntegerMap.put(alp[i], i);
        }
    }

    public List<String> findRepeatedDnaSequences(String s) {
        // 使用状态压缩
        // 使用位移
        int size = s.length();
        List<String> ans = new ArrayList<>();
        if (size <= 10) {
            return ans;
        }
        int curr = 0;
        int mask = 3;
        for (int i = 0; i < 12 - 1; i++) {
            // 一共32位,使用20位,遮罩12位
            mask = mask << 2;
            mask |= 3;
        }
        mask = mask << 20;
        char[] chars = s.toCharArray();
        curr |= characterIntegerMap.get(chars[0]);
        for (int i = 1; i < 10; i++) {
            curr = curr << 2;
            curr |= characterIntegerMap.get(chars[i]);
        }
        curr |= mask;
        Set<Integer> set = new HashSet<>();
        Set<Integer> visit = new HashSet<>();
        set.add(curr);
        for (int i = 10; i < size; i++) {
            curr = curr << 2;
            curr |= characterIntegerMap.get(chars[i]);
            curr |= mask;
            if (set.contains(curr) && !visit.contains(curr)) {
                ans.add(intToString(curr));
                visit.add(curr);
            }
            set.add(curr);
        }
        return ans;
    }

    private String intToString(int bit) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            stringBuilder.append(alp[3 & bit]);
            bit = bit >> 2;
        }
        stringBuilder.reverse();
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
    }
}