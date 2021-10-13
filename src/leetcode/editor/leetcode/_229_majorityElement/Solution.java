package leetcode.editor._229_majorityElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ret = new ArrayList<>();
        if (nums.length == 1) {
            ret.add(nums[0]);
            return ret;
        }
        Arrays.sort(nums);
        int count = 1;
        int last = Integer.MIN_VALUE;
        int retLast = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == last) {
                count++;
            } else {
                if (count > nums.length / 3) {
                    if (i - 1 >= 0 && retLast != last) {
                        ret.add(nums[i - 1]);
                        retLast = nums[i - 1];
                    } else if (retLast != nums[0]) {
                        ret.add(nums[0]);
                        retLast = nums[0];
                    }
                    if (ret.size() == 2) {
                        return ret;
                    }
                }
                count = 1;
                if (nums.length - i < nums.length / 3) {
                    break;
                }
            }
            last = nums[i];
        }
        if (count > nums.length / 3 && retLast != last) {
            ret.add(last);
        }
        return ret;
    }
}