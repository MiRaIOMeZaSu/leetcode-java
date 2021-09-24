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
        Deque<Node> queue = new LinkedList<>();
        Node curr = head;
        Node last = head;
        while (!queue.isEmpty() || curr != null) {
            if (curr == null) {
                curr = queue.poll();
                last.next = curr;
                curr.prev = last;
            }
            last = curr;
            if (curr.child != null) {
                queue.add(curr.child);
            }
            curr = curr.next;
        }
        return head;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Node head = new Node(1);
        Node _1 = new Node(2);
        Node _2 = new Node(3);
        head.child = _2;
        head.next = _1;
        _1.prev = head;
        solution.flatten(head);
    }
}