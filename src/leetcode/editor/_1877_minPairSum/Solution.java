package leetcode.editor._1877_minPairSum;

import java.lang.reflect.Array;
import java.util.*;

class Solution {
    int[] nums;
    int size;
    List<int[]> list = new ArrayList<>();
    int index;
    long total = 0L;

    public int minPairSum(int[] nums) {
        size = nums.length;
        Arrays.sort(nums);
        int last = nums[0];
        list.add(new int[]{last, 1});
        index = 0;
        for (int i = 1; i < size; i++) {
            if (nums[i] != last) {
                last = nums[i];
                list.add(new int[]{last, 1});
                index++;
            } else {
                list.get(index)[1]++;
            }
        }
        this.nums = nums;
        for (int i = 0; i < size; i++) {
            total += nums[i];
        }
        int start = Math.max(total / size + total % size == 0 ? 0 : 1, nums[size - 1] + nums[0]);
        int end = nums[size - 1] + nums[size - 2];
        int left = start;
        int right = end;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (statisfy(mid)) {
                // 满足
                right = mid;
            } else {
                // 不满足
                left = mid + 1;
            }
        }
        return right;
    }

    private boolean statisfy(int target) {
        long localTotal = total;
        int localSize = size;
        TreeMap<Integer, Integer> map = new TreeMap<>((o1, o2) -> o2 - o1);
        for (int i = index; i >= 0; i--) {
            map.put(list.get(i)[0], list.get(i)[1]);
        }
        while (!map.isEmpty()) {
            Map.Entry<Integer, Integer> entry = map.firstEntry();
            Integer next = map.higherKey(entry.getKey());
            if (next != null && next + entry.getKey() <= target) {
                return true;
            }
            int limit = target - entry.getKey() + 1;
            Map.Entry<Integer, Integer> temp = map.higherEntry(limit);
            if (temp == null) {
                return false;
            }
            if (entry.getValue() > temp.getValue()) {
                map.put(entry.getKey(), entry.getValue() - temp.getValue());
                map.remove(temp.getKey());
                localTotal -= (temp.getKey() + entry.getKey()) * temp.getValue();
                localSize -= 2 * temp.getValue();
            } else if (entry.getValue() < temp.getValue()) {
                map.put(temp.getKey(), temp.getValue() - entry.getValue());
                map.remove(entry.getKey());
                localTotal -= (temp.getKey() + entry.getKey()) * entry.getValue();
                localSize -= 2 * entry.getValue();
            } else {
                map.remove(entry.getKey());
                map.remove(temp.getKey());
                localTotal -= 2 * entry.getKey() * entry.getValue();
                localSize -= 2 * entry.getValue();
            }
            if (localTotal > 0 && localTotal / localSize > target) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.minPairSum(new int[]{4, 1, 5, 1, 2, 5, 1, 5, 5, 4});
        System.out.println(result);
    }
}