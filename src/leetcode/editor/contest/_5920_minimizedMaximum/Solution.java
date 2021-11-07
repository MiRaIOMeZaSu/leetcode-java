package leetcode.editor.contest._5920_minimizedMaximum;

class Solution {
    public int minimizedMaximum(int n, int[] quantities) {
        int right = quantities[0];
        int total = right;
        for (int i = 1; i < quantities.length; i++) {
            total += quantities[i];
            right = Math.max(right, quantities[i]);
        }
        int left = solve(n, total);
        // 使用二分法
        int ans = right;
        while (left <= right) {
            int mid = (left + right) >> 1;
            int needle = 0;
            int i = 0;
            for (; i < quantities.length && needle <= n; i++) {
                needle += solve(mid, quantities[i]);
            }
            if (i == quantities.length && needle <= n) {
                // mid满足条件
                ans = Math.min(ans, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private int solve(int divide, int dividor) {
        int ans = dividor / divide;
        ans += dividor % divide > 0 ? 1 : 0;
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.minimizedMaximum(6, new int[]{11, 6});
    }
}