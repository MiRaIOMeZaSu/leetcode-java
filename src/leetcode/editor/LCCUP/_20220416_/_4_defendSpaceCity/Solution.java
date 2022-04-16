package leetcode.editor.LCCUP._20220416_._4_defendSpaceCity;

import java.util.*;

class Solution {
    public int defendSpaceCity(int[] time, int[] position) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, Set<Integer>> hashMap = new HashMap<>();
        Set<Integer> timepointSet = new HashSet<>();
        List<Integer> timepoint = new ArrayList<>();
        int ans = 0;
        for (int i = 0; i < time.length; i++) {
            if (!timepointSet.contains(time[i])) {
                timepointSet.add(time[i]);
                timepoint.add(time[i]);
                map.put(time[i], new ArrayList<>());
                hashMap.put(time[i], new HashSet<>());
            }
            map.get(time[i]).add(position[i]);
            hashMap.get(time[i]).add(position[i]);
        }
        // 如何计算
        for (int i = 0; i < timepoint.size(); i++) {
            int currTime = timepoint.get(i);
            Set<Integer> lastTime = new HashSet<>();
            if (timepointSet.contains(currTime - 1)) {
                lastTime = hashMap.get(currTime - 1);
            }
            List<Integer> arr = map.get(timepoint.get(i));
            arr.sort(Integer::compareTo);
            // 可以开始计算
            // 当前的行动会影响之后的吗?
            // 实际上不会影响
            // 不关心前一个!
            // 但关心上一次
            // 优先维护上一次的
            // 可以维护多重的!
            for (int j = 0; j < arr.size(); ) {
                int currPos = arr.get(j);
                if (lastTime.contains(currPos)) {
                    ans += 1;
                    j++;
                    continue;
                }
                if (j + 1 < arr.size() && arr.get(j + 1) == currPos + 1) {
                    ans += 3;
                    j += 2;
                }
                ans += 2;
                j++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.defendSpaceCity(new int[]{1, 2, 1}, new int[]{6, 3, 3}));
        System.out.println(solution.defendSpaceCity(new int[]{1,1,1,2,2,3,5}, new int[]{1,2,3,1,2,1,3}));
    }
}