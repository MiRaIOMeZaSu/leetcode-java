package leetcode.editor.offer._36_treeToDoublyList;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Node _1 = new Node(4);
        Node _2 = new Node(2);
        Node _3 = new Node(5);
        _1.left = _2;
        _1.right = _3;
        Node _4 = new Node(1);
        _2.left = _4;
        Node _5 = new Node(3);
        _2.right = _5;
        solution.treeToDoublyList(_1);
    }
}
