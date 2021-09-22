package leetcode.editor._725_splitListToParts;

import java.util.List;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode[] splitListToParts(ListNode head, int k) {
        if (head == null) {
            return new ListNode[k];
        }
        // 先求出长度
        int size = getLen(head);
        int gap = size / k;
        int rest = size % k;
        int more = rest == 0 ? 0 : 1;
        ListNode headNode = head;
        ListNode currNode = head;
        int curr = 1;
        int index = 0;
        ListNode[] ans = new ListNode[k];
        ans[0] = head;
        while (currNode != null) {
            if (curr == gap + more) {
                // 可以开始填入
                ans[index] = headNode;
                index++;
                if (more > 0) {
                    rest--;
                }
                if (rest == 0) {
                    more = 0;
                }
                headNode = currNode.next;
                currNode.next = null;
                currNode = headNode;
                curr = 1;
                continue;
            }
            currNode = currNode.next;
            curr++;
        }
        return ans;
    }

    private int getLen(ListNode root) {
        ListNode pointer = root;
        int i = 0;
        while (pointer != null) {
            pointer = pointer.next;
            i++;
        }
        return i;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        solution.splitListToParts(head, 2);
    }
}