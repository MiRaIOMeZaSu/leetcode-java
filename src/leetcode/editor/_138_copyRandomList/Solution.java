package leetcode.editor._138_copyRandomList;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    HashMap<Integer, Node> visit = new HashMap<>();

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node ret = new Node(head.val);
        visit.put(ret.val, ret);
        Deque<Node> q = new LinkedList<>();
        q.push(head);
        while (!q.isEmpty()) {
            Node n = q.poll();
            Node nextN = visit.get(n.val);
            if (n.next != null) {
                if (!visit.containsKey(n.next.val)) {
                    visit.put(n.next.val, new Node(n.next.val));
                    q.push(n.next);
                }
                nextN.next = visit.get(n.next.val);
            }
            if (n.random != null) {
                if (!visit.containsKey(n.random.val)) {
                    visit.put(n.random.val, new Node(n.random.val));
                    q.push(n.random);
                }
                nextN.random = visit.get(n.random.val);
            }
        }
        return ret;
    }
}