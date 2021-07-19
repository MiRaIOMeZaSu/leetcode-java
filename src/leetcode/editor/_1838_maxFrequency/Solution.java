package leetcode.editor._1838_maxFrequency;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    int[] nums;
    int[] prefix;
    int result = 1;
    int k;

    public int maxFrequency(int[] nums, int k) {
        this.nums = nums;
        this.k = k;
        int size = nums.length;
        Arrays.sort(nums);
        int lastNum = 0;
        int count = 0;
        prefix = new int[size];
        prefix[0] = nums[0];
        result = 1;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                prefix[i] = prefix[i - 1] + nums[i];
            }
            int num = nums[i];
            if (lastNum != num) {
                int start = i - count - 1;
                if (start >= 0 && count != 0) {
                    if (prefix[start] + k >= (start + 1) * lastNum) {
                        result = Math.max(result, start + 1 + count);
                    } else if (nums[start] + k >= lastNum) {
                        // 开始往前寻找
                        int left = 0;
                        int right = start - 1;
                        getMiddle(left, right, start, lastNum, count);
                    }
                }
                lastNum = num;
                count = 1;
            } else {
                count++;
            }
        }
        int start = size - 1 - count;
        if (prefix[size - 1] + k >= nums[size - 1] * size) {
            return size;
        }
        getMiddle(0, start - 1, start, nums[size - 1], count);
        return result;
    }

    private void getMiddle(int left, int right, int start, int lastNum, int count) {
        if (left >= right) {
            return;
        }
        while (left < right) {
            int mid = (left + right) >> 1;
            int total = prefix[start] - prefix[mid];
            int suppose = (start - mid) * lastNum;
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
        int ret = solution.maxFrequency(new int[]{3, 9, 6}, 2);
        System.out.println(ret);
    }
}