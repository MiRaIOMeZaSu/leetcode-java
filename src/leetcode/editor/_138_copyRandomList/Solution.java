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
        Deque<Node[]> q = new LinkedList<>();
        Node curr = head;
        visit.put(curr.val, new ArrayList<>());
        Node ret = new Node(curr.val);
        visit.get(curr.val).add(new Node[]{curr, ret});
        q.push(new Node[]{curr, ret});
        curr = curr.next;
        while (curr != null) {
            if (!visit.containsKey(curr.val)) {
                visit.put(curr.val, new ArrayList<>());
            }
            Node tempNode = new Node(curr.val);
            q.peek()[1].next = tempNode;
            visit.get(curr.val).add(new Node[]{curr, tempNode});
            q.push(new Node[]{curr, tempNode});
            curr = curr.next;
        }
        while (!q.isEmpty()) {
            Node[] arr = q.pollLast();
            if (arr[0].random != null) {
                arr[1].random = getNode(arr[0].random);
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