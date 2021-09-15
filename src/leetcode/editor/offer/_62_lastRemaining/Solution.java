package leetcode.editor.offer._62_lastRemaining;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {
    public int lastRemaining(int n, int m) {
        // 构成一个环链表
        // 使用线段树进行模拟
        // m会不断减小
        // 构成一个环链表
        // 直接计算出结果?
        ListNode head = new ListNode(0);
        ListNode curr = head;
        for (int i = 1; i < n; i++) {
            curr.next = new ListNode(i);
            curr = curr.next;
        }
        int temp = (m + 1) % n - 1;
        int currM = temp > 0 ? temp : m;
        curr.next = head;
        ListNode last = curr;
        curr = head;
        while (curr.next != curr) {
            for (int i = 1; i < currM; i++) {
                last = curr;
                curr = curr.next;
            }
            System.out.println(curr.val);
            last.next = curr.next;
            curr = last.next;
            n--;
            temp = (m + 1) % n - 1;
            currM = temp > 0 ? temp : m;
        }
        return curr.val;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.lastRemaining(5, 3);
    }
}