package leetcode.editor._763_partitionLabels;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> partitionLabels(String s) {
        // 直接遍历
        int size = s.length();
        char a = 'a';
        int[] lastIndex = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < size; i++) {
            lastIndex[chars[i] - a] = i;
        }
        List<Integer> ans = new ArrayList<>();
        int left, right;
        int index = 0;
        while (index < size) {
            left = index;
            right = lastIndex[chars[index] - a];
            while (index < right) {
                index++;
                right = Math.max(right, lastIndex[chars[index] - a]);
            }
            // 此时index==right
            // 此时可以开始分割
            ans.add(right - left + 1);
            index++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.partitionLabels("ababcbacadefegdehijhklij"));
    }
}