package leetcode.editor.leetcode._6039_maximumProduct;

import com.sun.security.jgss.GSSUtil;

import java.util.Arrays;

class Solution {
    private final static int PIVOT = 1000000007;

    public int maximumProduct(int[] nums, int k) {
        // 先排序,再前缀和
        Arrays.sort(nums);
        long[] prex = new long[nums.length];
        prex[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prex[i] = prex[i - 1] + nums[i];
        }
        long sum = prex[nums.length - 1];
        long avg = (sum + k) / nums.length;
        if (avg >= nums[nums.length - 1]) {
            // 基本上均等
            long rest = sum + k - avg * nums.length;
            long ans = 1;
            long avgPlus = rest / nums.length;
            int specialPlusCount = (int)(rest - nums.length * avgPlus);
            for (int i = 0; i < specialPlusCount; i++) {
                ans *= avg + 1 + avgPlus;
                ans %= PIVOT;
            }
            for (int i = specialPlusCount; i < nums.length; i++) {
                ans *= avg + avgPlus;
                ans %= PIVOT;
            }
            return (int) (ans % PIVOT);
        }
        // 使用二分法
        int left = 0;
        int right = nums.length - 1;
        int targetIndex = left;
        long targetNeedle = 0;
        while (left <= right) {
            int mid = (left + right) >> 1;
            // 数量为mid + 1
            long needle = (long)nums[mid] * (long)(mid + 1) - prex[mid];
            if (needle > k) {
                // 无法实现
                right = mid - 1;
            } else {
                targetIndex = mid;
                targetNeedle = needle;
                left = mid + 1;
            }
        }
        // 当下标为targetIndex + 1时不满足!
        long rest = k - targetNeedle;
        long ans = 1;
        long avgPlus = rest / (targetIndex + 1);
        int specialPlusCount = (int)(rest - (targetIndex + 1) * avgPlus);
        for (int i = 0; i < specialPlusCount; i++) {
            ans *= nums[targetIndex] + avgPlus + 1;
            ans %= PIVOT;
        }
        for (int i = specialPlusCount; i <= targetIndex; i++) {
            ans *= nums[targetIndex] + avgPlus;
            ans %= PIVOT;
        }
        for (int i = targetIndex + 1; i < nums.length; i++) {
            ans *= nums[i];
            ans %= PIVOT;
        }
        return (int) (ans % PIVOT);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.maximumProduct(new int[]{0, 4}, 5));
//        System.out.println(solution.maximumProduct(new int[]{124, 512, 23, 6, 1, 2, 47, 1, 642, 47, 234, 7123, 7}, 351));
//        System.out.println(solution.maximumProduct(new int[]{21, 100}, 58));
    }
}