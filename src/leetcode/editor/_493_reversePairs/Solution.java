package leetcode.editor._493_reversePairs;

import java.util.*;

class Solution {
    public int reversePairs(int[] nums) {
        // 往后存且一边存一遍计算即可,此时必然满足i < j
        // 数字的大小不限!
        // 数组长度最长为50000
        // 实际是可以实现线段树的(需要十万空间
        int ret = 0;
        HashMap<Integer, Integer[]> map = new HashMap<>(60);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!map.containsKey(num)) {
                map.put(num, new Integer[]{0, 0});
                list.add(num);
            }
            map.get(num)[0]++;
            int tempNum = num >> 1;
            if (!map.containsKey(tempNum)) {
                list.add(tempNum);
                map.put(tempNum, new Integer[]{0, 0});
            }
        }
        // 要寻找的是一个越小越好的数
        list.sort(Comparator.naturalOrder());
        for (int i = 0; i < list.size(); i++) {
            map.get(list.get(i))[1] = i;
        }
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            map.get(num)[0]--;
            int target = num >> 1;
            if ((num | 1) == num) {
                // num为奇数,target可以包含
                ret += map.get(target)[0];
            }
            int index = map.get(target)[1];
            for (int j = index - 1; j >= 0; j--) {
                ret += map.get(list.get(j))[0];
            }
        }
        return ret;
    }
}