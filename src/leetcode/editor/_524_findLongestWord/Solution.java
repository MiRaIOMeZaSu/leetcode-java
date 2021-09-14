package leetcode.editor._524_findLongestWord;

import java.util.ArrayList;
import java.util.List;

class Solution {
    char pivot = 'a';
    int[][] dp;
    String s;

    public String findLongestWord(String s, List<String> dictionary) {
        // 构造数组字典
        this.s = s;
        int size = s.length();
        dp = new int[size + 1][26];
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                dp[i][j] = dp[i + 1][j];
            }
            dp[i][s.charAt(i) - pivot] = i + 1;
        }
        // 构造完成，开始搜索
        String result = "";
        for (int i = 0; i < dictionary.size(); i++) {
            if (solve(dictionary.get(i), 0, 0)) {
                if (result == "") {
                    result = dictionary.get(i);
                    continue;
                }
                result = min(result, dictionary.get(i));
            }
        }
        return result;
    }

    private String min(String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
        if (aLen > bLen) {
            return a;
        } else if (aLen != bLen) {
            return b;
        }
        for (int i = 0; i < aLen; i++) {
            char chA = a.charAt(i);
            char chB = b.charAt(i);
            if (chA < chB) {
                return a;
            } else if (chA > chB) {
                return b;
            }
        }
        return a;
    }

    private boolean solve(String pat, int patIndex, int tableIndex) {
        int a = dp[tableIndex][pat.charAt(patIndex) - pivot];
        patIndex++;
        tableIndex = a;
        while (a != 0 && patIndex < pat.length()) {
            a = dp[tableIndex][pat.charAt(patIndex) - pivot];
            if (a == 0) {
                break;
            }
            patIndex++;
            tableIndex = a;
        }
        if (patIndex >= pat.length() && a != 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> list = new ArrayList<>();
        String[] arr = new String[]{"pbccyy","wpzjmdgl","z","mnfs","bpzzyscc","mtcufnrevh","lkymi","ia","ejw","tslyawz","qhwvritvxo","koau","ldrfgsdr","vm","l","rvv","oefj","upd","t","zebluyjiy","h","qig","tgfdppvdn","yfznucyvj","hmxvyesw","srghsput","iox","nkewca","d","ishsupl","fbg","ltvrekguxl","pgepkyokr","qamfgoypi","ejjxn"};
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        System.out.println(solution.findLongestWord("fxbkjqjsrxxybhtvvnnbjmdxtafnlehirmmldctvxswzfhgmxw", list));
    }
}