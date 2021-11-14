package leetcode.editor.contest._5929_friendRequests;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    int[] set;

    private int getRoot(int i) {
        int curr = i;
        while (set[curr] != curr) {
            curr = set[curr];
        }
        set[i] = curr;
        return curr;
    }

    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        // 并查集
        set = new int[n];
        for (int i = 0; i < n; i++) {
            set[i] = i;
        }
        Set[] sets = new HashSet[n];
        for (int i = 0; i < restrictions.length; i++) {
            int min = Math.min(restrictions[i][0], restrictions[i][1]);
            int max = Math.max(restrictions[i][0], restrictions[i][1]);
            if (sets[min] == null) {
                sets[min] = new HashSet();
            }
            sets[min].add(max);
        }
        boolean[] ans = new boolean[requests.length];
        for (int i = 0; i < requests.length; i++) {
            int min = Math.min(requests[i][0], requests[i][1]);
            int max = Math.max(requests[i][0], requests[i][1]);
            if (sets[min] != null && sets[min].contains(max)) {
                ans[i] = false;
            } else if (getRoot(min) != getRoot(max)) {
                // 会出现树的合并
                Set<Integer> pool = new HashSet<>();
                pool.add(set[min]);
                pool.add(set[max]);
                boolean flag = false;
                for (int j = 0; j < restrictions.length; j++) {
                    if (pool.contains(getRoot(restrictions[j][0])) && pool.contains(getRoot(restrictions[j][1]))) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    // 能
                    set[max] = set[min];
                    ans[i] = true;
                }
            } else {
                ans[i] = true;
            }
        }
        return ans;
    }
}