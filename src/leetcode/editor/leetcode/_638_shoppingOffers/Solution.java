package leetcode.editor.leetcode._638_shoppingOffers;

import java.util.ArrayList;
import java.util.List;

class Solution {
    int[][][][][][] dp;
    boolean[][][][][][] visit;
    int[] len = new int[6];
    int ans;
    List<Integer> price;
    int size;

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        size = price.size();
        this.price = price;
        for (int i = 0; i < needs.size(); i++) {
            len[i] = needs.get(i) + 1;
        }
        for (int i = needs.size(); i < 6; i++) {
            len[i] = 1;
        }
        visit = new boolean[len[0]][len[1]][len[2]][len[3]][len[4]][len[5]];
        dp = new int[len[0]][len[1]][len[2]][len[3]][len[4]][len[5]];
        for (int i = 0; i < special.size(); i++) {
            List<Integer> temp = special.get(i);
            int p = temp.get(size);
            temp.remove(size);
            while (temp.size() < 6) {
                temp.add(0);
            }
            temp.add(p);
            solve(special.get(i), 0, new int[]{0, 0, 0, 0, 0, 0});
        }
        ans = 0;
        for (int i = 0; i < size; i++) {
            ans += price.get(i) * needs.get(i);
        }
        compute(0, new int[]{0, 0, 0, 0, 0, 0}, 0);
        return ans;
    }

    private void solve(List<Integer> special, int curr, int[] nums) {
        if (size == curr) {
            int index = 1;
            while (true) {
                if (dp[nums[0]][nums[1]][nums[2]][nums[3]][nums[4]][nums[5]] == 0 && !(nums[0] == 0 && nums[1] == 0 && nums[2] == 0 && nums[3] == 0 && nums[4] == 0 && nums[5] == 0)) {
                    break;
                }
                boolean flag = false;
                for (int i = 0; i < size; i++) {
                    if (nums[i] + index * special.get(i) >= len[i]) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    break;
                }
                if (!visit[nums[0] + special.get(0) * index][nums[1] + special.get(1) * index][nums[2] + special.get(2) * index][nums[3] + special.get(3) * index][nums[4] + special.get(4) * index][nums[5] + special.get(5) * index]) {
                    dp[nums[0] + special.get(0) * index][nums[1] + special.get(1) * index][nums[2] + special.get(2) * index][nums[3] + special.get(3) * index][nums[4] + special.get(4) * index][nums[5] + special.get(5) * index] =
                            dp[nums[0]][nums[1]][nums[2]][nums[3]][nums[4]][nums[5]] + special.get(special.size() - 1) * index;
                    visit[nums[0] + special.get(0) * index][nums[1] + special.get(1) * index][nums[2] + special.get(2) * index][nums[3] + special.get(3) * index][nums[4] + special.get(4) * index][nums[5] + special.get(5) * index] = true;
                } else {
                    dp[nums[0] + special.get(0) * index][nums[1] + special.get(1) * index][nums[2] + special.get(2) * index][nums[3] + special.get(3) * index][nums[4] + special.get(4) * index][nums[5] + special.get(5) * index] =
                            Math.min(
                                    dp[nums[0] + special.get(0) * index][nums[1] + special.get(1) * index][nums[2] + special.get(2) * index][nums[3] + special.get(3) * index][nums[4] + special.get(4) * index][nums[5] + special.get(5) * index]
                                    , dp[nums[0]][nums[1]][nums[2]][nums[3]][nums[4]][nums[5]] + special.get(special.size() - 1) * index
                            );
                }
                index++;
            }
            return;
        }
        for (int i = len[curr] - 1 - special.get(curr); i >= 0; i--) {
            nums[curr] = i;
            solve(special, curr + 1, nums);
        }
    }

    private void compute(int curr, int[] nums, int currPrice) {
        if (size == curr) {
            if (visit[nums[0]][nums[1]][nums[2]][nums[3]][nums[4]][nums[5]]) {
                ans = Math.min(ans, dp[nums[0]][nums[1]][nums[2]][nums[3]][nums[4]][nums[5]] + currPrice);
            }
            return;
        }
        for (int i = len[curr] - 1; i >= 0; i--) {
            nums[curr] = i;
            compute(curr + 1, nums, currPrice + (len[curr] - 1 - i) * price.get(curr));
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> price = new ArrayList<>();
        price.add(2);
        price.add(5);
        List<List<Integer>> special = new ArrayList<>();
        special.add(new ArrayList<>());
        special.get(0).add(3);
        special.get(0).add(0);
        special.get(0).add(5);
        special.add(new ArrayList<>());
        special.get(1).add(1);
        special.get(1).add(2);
        special.get(1).add(10);
        List<Integer> needs = new ArrayList<>();
        needs.add(3);
        needs.add(2);
        solution.shoppingOffers(price, special, needs);
    }
}