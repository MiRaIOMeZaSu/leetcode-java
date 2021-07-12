package leetcode.editor._1910_removeOccurrences;

import java.util.List;

public class Solution {
    static char start = 'a';

    class ListNode {
        char val;
        ListNode next;

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
            curr = curr.next;
        }
        curr = head.next;
        int index = 0;
        ListNode pres1 = head.next;
        ListNode pres2 = head.next;
        int count1 = 0;
        int count2 = 0;
        int matchIndex = 0;
        while (size >= otherSize && curr.next != null) {
            int key = curr.val - start;
            matchIndex = dp[matchIndex][key];
            if (matchIndex == otherSize) {
                // 此时完全匹配
                pres1.next = curr.next;
                size -= otherSize;
                index -= otherSize + otherSize - 2;
                if (index < 0) {
                    index = 0;
                }
                curr = pres2;
                matchIndex = 0;
                continue;
            }
            curr = curr.next;
            index++;
            if (count1 == otherSize) {
                pres1 = pres1.next;
            } else {
                count1++;
            }

            if (count2 == otherSize * 2) {
                pres2 = pres2.next;
            } else {
                count2++;
            }
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
        solution.removeOccurrences("daabcbaabcbc",
                "abc");
    }
}