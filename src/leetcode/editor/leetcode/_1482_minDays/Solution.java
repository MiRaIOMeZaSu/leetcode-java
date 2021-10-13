package leetcode.editor._1482_minDays;


import java.util.*;

class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        // 不是使用移除而是选择
        // 进行二分
        HashSet<Integer> set = new HashSet<>();
        List<Integer> day = new ArrayList<>();
        for (int i : bloomDay) {
            if (!set.contains(i)) {
                set.add(i);
                day.add(i);
            }
        }
        day.sort(Comparator.comparingInt(o -> o));
        int left = 0;
        int right = day.size() - 1;
        int ret = Integer.MAX_VALUE;
        // TODO: 此处直接使用日期而不是存在的日期二分指向标做设置更好
        while (right >= left) {
            int mid = (right + left) >> 1;
            boolean flag = fullfil(bloomDay, m, k, day.get(mid));
            if (flag) {
                // 此时满足
                ret = Math.min(ret, day.get(mid));
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ret == Integer.MAX_VALUE ? -1 : ret;
    }

    boolean fullfil(int[] bloomDay, int m, int k, int pivot) {
        int currBloom = 0;
        int size = bloomDay.length;
        int i = 0;
        int len = 0;
        while (currBloom < m && i < size) {
            if (bloomDay[i] <= pivot) {
                len++;
            } else {
                currBloom += len / k;
                len = 0;
            }
            i++;
        }
        currBloom += len / k;
        return currBloom >= m;
    }
}