package leetcode.editor.contest._5970_maximumInvitations;

import java.util.ArrayList;
import java.util.List;

class Solution {
    boolean[] globalVisited;
    boolean[] visited;
    int size;
    int[] favorite;
    int more;

    public int maximumInvitations(int[] favorite) {
        // 如何成为一个回环?
        // 动态规划?
        int ans = 0;
        more = 0;
        this.favorite = favorite;
        size = favorite.length;
        globalVisited = new boolean[size];
        List[] beloved = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            if (beloved[favorite[i]] == null) {
                beloved[favorite[i]] = new ArrayList();
            }
            beloved[favorite[i]].add(i);
        }
        out:
        for (int i = 0; i < size; i++) {
            List<Integer> tempGlobalVisited = new ArrayList<>();
            if (globalVisited[i]) {
                continue out;
            }
            visited = new boolean[size];
            int left = i;
            int right = favorite[i];
            visited[left] = true;
            visited[right] = true;
            tempGlobalVisited.add(left);
            tempGlobalVisited.add(right);
            int curr = 2;
            while (favorite[right] != right - 1) {
                if (favorite[right] == left) {
                    ans = Math.max(ans, curr);
                    for (int j = 0; j < tempGlobalVisited.size(); j++) {
                        globalVisited[tempGlobalVisited.get(j)] = true;
                    }
                    continue out;
                }
                if (visited[favorite[right]]) {
                    // 本轮失败
                    continue out;
                }
                right = favorite[right];
                visited[right] = true;
                tempGlobalVisited.add(right);
                curr++;
            }
            for (int j = 0; j < tempGlobalVisited.size(); j++) {
                globalVisited[tempGlobalVisited.get(j)] = true;
            }
            // 此时可以继续
            // 此时两边为left,right,且均无他求
            // 在喜欢两者的人中找
            more = 0;
            solve(0, visited, left, right, 0);
            ans = Math.max(more + curr, ans);
        }
        return ans;
    }

    private void solve(int index, boolean[] visited, int left, int right, int curr) {
        for (int i = index; i < size; i++) {
            if (!visited[i]) {
                if (favorite[i] == left) {
                    visited[i] = true;
                    solve(i + 1, visited, i, right, curr + 1);
                    visited[i] = false;
                } else if (favorite[i] == right) {
                    visited[i] = true;
                    solve(i + 1, visited, left, i, curr + 1);
                    visited[i] = false;
                }
                more = Math.max(curr, more);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maximumInvitations(new int[]{3, 0, 1, 4, 1}));
        System.out.println(solution.maximumInvitations(new int[]{1, 2, 3, 4, 5, 6, 3, 8, 9, 10, 11, 8}));
    }
}