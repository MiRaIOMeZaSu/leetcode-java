package leetcode.editor.offer._36_treeToDoublyList;

import java.util.Deque;
import java.util.LinkedList;

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}

class Solution {
    public Node treeToDoublyList(Node root) {
        // 使用栈或直接使用函数栈
        if (root == null) {
            return null;
        }
        Node[] arr = solve(root);
        arr[0].left = arr[1];
        arr[1].right = arr[0];
        return arr[0];
    }

    private Node[] solve(Node root) {
        if (root == null) {
            return null;
        }
        Node[] first = solve(root.left);
        Node[] second = solve(root.right);
        if (first == null || second == null) {
            if (first == null && second == null) {
                return new Node[]{root, root};
            } else if (first == null) {
                second[0].left = root;
                root.right = second[0];
                second[0] = root;
                return second;
            } else {
                first[1].right = root;
                root.left = first[1];
                first[1] = root;
                return first;
            }
        }
        first[1].right = root;
        root.left = first[1];
        second[0].left = root;
        root.right = second[0];
        // 返回头和尾
        return new Node[]{first[0], second[1]};
    }
}