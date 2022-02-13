package leetcode.editor.contest._6006_minimumRemoval;

import java.util.Arrays;

class Solution {
    public long minimumRemoval(int[] beans) {
        // 排序
        // 遍历即可
        int size = beans.length;
        long sum = 0;
        for (int i = 0; i < size; i++) {
            sum += beans[i];
        }
        long ans = sum;
        Arrays.sort(beans);
        for (int i = 0; i < size; i++) {
            if (beans[i] == 0) {
                continue;
            }
            if (i > 0 && beans[i] == beans[i - 1]) {
                continue;
            }
            long restBagCount = size - i;
            long everyBagBeanCount = beans[i];
            ans = Math.min(ans, sum - restBagCount * everyBagBeanCount );
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minimumRemoval(new int[]{1, 2}));
    }
}