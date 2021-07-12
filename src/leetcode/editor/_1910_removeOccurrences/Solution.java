package leetcode.editor._1910_removeOccurrences;

import java.util.List;

public class Solution {
    static char start = 'a';

    class ListNode {
        char val;
        ListNode next;
        ListNode pres;

        ListNode(char val) {
            this.val = val;
        }
    }

    private static int[][] kmp(String string) {
        int size = string.length();
        int[][] dp = new int[size][26];
        dp[0][string.charAt(0) - start] = 1;
        // 对于与目标字符串匹配的情况,x是与正常匹配向左错位一格的,相当于每次匹配失败后走向的目标,此时再次从dp中获取已经计算过的情况
        int x = 0;
        for (int i = 1; i < size; i++) {
            int key = string.charAt(i) - start;
            for (int j = 0; j < 26; j++) {
                if (j == key) {
                    dp[i][j] = i + 1;
                } else {
                    dp[i][j] = dp[x][j];
                }
            }
            x = dp[x][key];
        }
        return dp;
    }

    public String removeOccurrences(String s, String part) {
        int[][] dp = kmp(part);
        ListNode head = new ListNode('a');
        int size = s.length();
        int otherSize = part.length();
        ListNode curr = head;
        for (int i = 0; i < size; i++) {
            curr.next = new ListNode(s.charAt(i));
            curr.next.pres = curr;
            curr = curr.next;
        }
        curr = head.next;
        int matchIndex = 0;
        while (size >= otherSize && curr != null) {
            int key = curr.val - start;
            matchIndex = dp[matchIndex][key];
            if (matchIndex == otherSize) {
                // 此时完全匹配
                ListNode pres1 = curr;
                for (int i = 0; i < otherSize; i++) {
                    pres1 = pres1.pres;
                }
                pres1.next = curr.next;
                if (curr.next != null) {
                    curr.next.pres = pres1;
                }
                size -= otherSize;
                curr = pres1;
                int count = 0;
                while (count < otherSize - 2 && curr != head.next && curr.pres != null) {
                    curr = curr.pres;
                    count++;
                }
                if (curr.pres == null) {
                    curr = head.next;
                }
                matchIndex = 0;
                continue;
            }
            curr = curr.next;
        }
        StringBuilder stringBuilder = new StringBuilder();
        curr = head.next;
        while (curr != null) {
            stringBuilder.append(curr.val);
            curr = curr.next;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String res = solution.removeOccurrences("yjyjqnaxlbqnaxlbfss",
                "yjqnaxlb");
        System.out.println(res);
    }
}