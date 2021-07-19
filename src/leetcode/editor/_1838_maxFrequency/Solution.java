package leetcode.editor._1838_maxFrequency;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    int[] nums;
    long[] prefix;
    int result = 1;
    long k;

    public int maxFrequency(int[] nums, long k) {
        this.nums = nums;
        this.k = k;
        int size = nums.length;
        Arrays.sort(nums);
        long lastNum = 0;
        int count = 0;
        prefix = new long[size];
        prefix[0] = nums[0];
        result = 1;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                prefix[i] = prefix[i - 1] + nums[i];
            }
            long num = nums[i];
            if (lastNum != num) {
                int start = i - count - 1;
                if (start >= 0 && count != 0) {
                    if (prefix[start] + k >= (start + 1) * lastNum) {
                        result = Math.max(result, start + 1 + count);
                    } else if (nums[start] + k >= lastNum) {
                        // 开始往前寻找
                        int left = 0;
                        int right = start - 1;
                        result = Math.max(result, count + 1);
                        getMiddle(left, right, start, lastNum, count);
                    }
                }
                lastNum = num;
                result = Math.max(result, count);
                count = 1;
            } else {
                count++;
            }
        }
        if (count == size) {
            return size;
        }
        int start = size - 1 - count;
        if (prefix[size - 1] + k >= nums[size - 1] * (long) size) {
            return size;
        }
        if (start >= 0 && nums[start] != nums[size - 1] && nums[start] + k >= nums[size - 1]) {
            result = Math.max(result, count + 1);
            getMiddle(0, start - 1, start, nums[size - 1], count);
        }
        return result;
    }

    private void getMiddle(int left, int right, int start, long lastNum, int count) {
        if (left >= right) {
            return;
        }
        while (left < right) {
            int mid = (left + right) >> 1;
            long total = prefix[start] - prefix[mid];
            long suppose = (start - mid) * lastNum;
            if (total + k > suppose) {
                right = mid;
            } else if (total + k < suppose) {
                left = mid + 1;
            } else {
                result = Math.max(result, count + start - mid);
                return;
            }
        }
        result = Math.max(result, count + start - right);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        long ret = solution.maxFrequency(new int[]{3, 9, 6}, 2);
        System.out.println(ret);
    }
}