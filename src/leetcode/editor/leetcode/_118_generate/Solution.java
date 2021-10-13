package leetcode.editor.leetcode._118_generate;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> base = new ArrayList<>();
        base.add(1);
        ans.add(base);
        List<Integer> last = base;
        for (int i = 1; i < numRows; i++) {
            List<Integer> next = new ArrayList<>();
            next.add(1);
            for (int j = 0; j + 1 < last.size(); j++) {
                next.add(last.get(j) + last.get(j + 1));
            }
            next.add(1);
            ans.add(next);
            last = next;
        }
        return ans;
    }
}