package leetcode.editor._82_deleteDuplicates;

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

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // 移除重复的数字结点
        // 已排序
        if (head == null) {
            return head;
        }
        // 对head进行处理
        while (head != null) {
            if (head.next != null && head.val == head.next.val) {
                int pivot = head.val;
                while (head!=null&&head.val == pivot) {
                    head = head.next;
                }
            } else {
                break;
            }
        }
        if (head==null){
            return head;
        }
        ListNode curr = head.next;
        ListNode last = head;
        while (curr != null) {
            if (curr.next != null && curr.val == curr.next.val) {
                int pivot = curr.val;
                while (curr!=null&&curr.val == pivot) {
                    curr = curr.next;
                }
                last.next = curr;
                // 此时的curr是另一个值
            }else {
                last = curr;
            }
            curr = last.next;
        }
        return head;
    }
}