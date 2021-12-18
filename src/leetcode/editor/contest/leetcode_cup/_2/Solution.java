package leetcode.editor.contest.leetcode_cup._2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int maxmiumScore(int[] cards, int cnt) {
        // 使用树?
        // 只能选择偶数个奇数!
        List<Integer> odd = new ArrayList<>();
        List<Integer> dou = new ArrayList<>();
        int size = cards.length;
        for (int i = 0; i < size; i++) {
            if (cards[i] % 2 == 0) {
                dou.add(cards[i]);
            } else {
                odd.add(cards[i]);
            }
        }
        dou.sort(Comparator.reverseOrder());
        odd.sort(Comparator.reverseOrder());

        for (int i = 1; i < dou.size(); i++) {
            dou.set(i, dou.get(i - 1) + dou.get(i));
        }
        for (int i = 1; i < odd.size(); i++) {
            odd.set(i, odd.get(i - 1) + odd.get(i));
        }
        if (odd.isEmpty()) {
            return dou.get(cnt - 1);
        } else if (dou.isEmpty()) {
            if (cnt % 2 == 1) {
                return 0;
            }
            return odd.get(cnt - 1);
        }
        int result = 0;
        int start = cnt - dou.size();

        if (start <= 0) {
            result = dou.get(cnt - 1);
            start = 2;
        } else if (start % 2 == 1) {
            start += 1;
        }
        // 如果start非常大
        if (odd.size() >= cnt && cnt % 2 == 0) {
            result = Math.max(result, odd.get(cnt - 1));
        }
        for (int i = start; i < cnt && i <= odd.size(); i += 2) {
            result = Math.max(result, odd.get(i - 1) + dou.get(cnt - i - 1));
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxmiumScore(new int[]{2, 2, 2, 2, 3, 1, 1, 2, 6, 5, 8, 4, 1, 3, 6, 8, 9, 5, 4, 6, 8, 4, 2}, 3));
    }
}