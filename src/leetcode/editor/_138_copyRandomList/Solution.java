package leetcode.editor._138_copyRandomList;

import java.util.*;

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
    HashMap<Integer, List<Node[]>> visit = new HashMap<>();

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node ret = new Node(head.val);
        List<Node[]> temp = new ArrayList<>();
        temp.add(new Node[]{head, ret});
        visit.put(ret.val, temp);
        Deque<Node> q = new LinkedList<>();
        q.push(head);
        while (!q.isEmpty()) {
            Node n = q.poll();
            Node nextN = getNode(n);
            if (n.next != null) {
                if (!visit.containsKey(n.next.val)) {
                    if (!visit.containsKey(n.next.val)) {
                        visit.put(n.next.val, new ArrayList<>());
                    }
                    Node tempNode = new Node(n.next.val);
                    visit.get(n.next.val).add(new Node[]{n.next, tempNode});
                    nextN.next = tempNode;
                    q.push(n.next);
                } else {
                    nextN.next = getNode(n.next);
                }
            }
            if (n.random != null) {
                if (!visit.containsKey(n.random.val)) {
                    if (!visit.containsKey(n.random.val)) {
                        visit.put(n.random.val, new ArrayList<>());
                    }
                    Node tempNode = new Node(n.random.val);
                    visit.get(n.random.val).add(new Node[]{n.random, tempNode});
                    nextN.random = tempNode;
                    q.push(n.random);
                } else {
                    nextN.random = getNode(n.random);
                }
            }
        }
        return ret;
    }

    Node getNode(Node node) {
        List<Node[]> arr = visit.get(node.val);
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i)[0] == node) {
                return arr.get(i)[1];
            }
        }
        return null;
    }
}