package leetcode.editor._430_flatten;

import java.util.Deque;
import java.util.LinkedList;

/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/
class Node {
    Node(int val) {
        this.val = val;
    }

    public int val;
    public Node prev;
    public Node next;
    public Node child;
};

class Solution {
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        // 使用递归算法
        return solve(head)[0];
    }

    private Node[] solve(Node head) {
        Node[] ans = new Node[2];
        ans[0] = head;
        Node curr = head;
        while (curr != null) {
            ans[1] = curr;
            if (curr.child != null) {
                Node[] temp = solve(curr.child);
                curr.child = null;
                Node next = curr.next;
                if (next != null) {
                    next.prev = temp[1];
                    temp[1].next = next;
                } else {
                    ans[1] = temp[1];
                }
                curr.next = temp[0];
                temp[0].prev = curr;
                curr = next;
            } else {
                curr = curr.next;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Node head = new Node(1);
        Node _1 = new Node(2);
        Node _2 = new Node(3);
        head.child = _2;
        head.next = _1;
        _1.prev = head;
        Node ans = solution.flatten(head);
        System.out.println(ans.val);
    }
}