package leetcode.editor.offer._38_permutation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class Solution {
    int[] table = new int[26];
    HashSet<String> set = new HashSet<>();
    int target;
    List<String> ret = new ArrayList<>();

    public String[] permutation(String s) {
        target = s.length();
        for (int i = 0; i < target; i++) {
            int index = s.charAt(i) - 'a';
            table[index]++;
        }
        solve(0, "");
        return ret.toArray(String[]::new);
    }

    private void solve(int index, String string) {
        if (set.contains(string)) {
            return;
        }
        set.add(string);
        if (index >= target) {
            ret.add(string);
            return;
        }
        for (int i = 0; i < 26; i++) {
            if (table[i] > 0) {
                table[i]--;
                String next = string;
                char temp = (char) ('a' + i);
                next += temp;
                solve(index + 1, next);
                table[i]++;
            }
        }
    }
}