package leetcode.editor._528_pickIndex;

import java.util.*;

class Solution {
    List<Integer> list = new ArrayList<>();
    Map<Integer, List<Integer>> map = new HashMap<>();
    Random r = new Random(System.currentTimeMillis());
    int total = 0;
    int[] arr;

    public Solution(int[] w) {
        int size = w.length;
        for (int i = 0; i < size; i++) {
            if (!map.containsKey(w[i])) {
                map.put(w[i], new ArrayList<>());
                list.add(w[i]);
            }
            map.get(w[i]).add(i);
        }
        list.sort(Comparator.naturalOrder());
        arr = new int[list.size()];
        arr[0] = list.get(0) * map.get(list.get(0)).size();
        for (int i = 1; i < list.size(); i++) {
            arr[i] = map.get(list.get(i)).size() * list.get(i);
            arr[i] += arr[i - 1];
        }
        total = arr[list.size() - 1];
    }

    public int pickIndex() {
        // 使用二分法
        int next = r.nextInt(total) + 1;
        if (next <= arr[0]) {
            return randomChoice(map.get(list.get(0)));
        }
        int left = 1;
        int right = list.size() - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr[mid] < next) {
                left = mid + 1;
            } else if (next > arr[mid - 1]) {
                // 此时同时满足next<= arr[mid],可直接退出
                return randomChoice(map.get(list.get(mid)));
            } else {
                // 此时next <= arr[mid],且next <= arr[mid - 1]
                right = mid - 1;
            }
        }
        return randomChoice(map.get(list.get(right)));
    }

    private int randomChoice(List<Integer> l) {
        if (l.size() == 1) {
            return l.get(0);
        }
        return l.get(r.nextInt(l.size()));
    }

    public static void main(String[] args) {
        Solution obj = new Solution(new int[]{10,7,8,10});
        int param_1 = obj.pickIndex();
        int param_2 = obj.pickIndex();
        int param_3 = obj.pickIndex();
        int param_4 = obj.pickIndex();
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */