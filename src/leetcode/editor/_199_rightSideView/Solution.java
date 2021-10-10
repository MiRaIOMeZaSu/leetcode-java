package leetcode.editor._199_rightSideView;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class MyTreeNode {
    int level = 0;
    TreeNode node = null;

    MyTreeNode(TreeNode node, int level) {
        this.node = node;
        this.level = level;
    }
}

class Solution {
    List<Integer> ans = new ArrayList<>();

    public List<Integer> rightSideView(TreeNode root) {
        // 使用广度优先!
        if (root == null) {
            return ans;
        }
        Deque<MyTreeNode> deque = new LinkedList<>();
        deque.add(new MyTreeNode(root, 0));
        while (!deque.isEmpty()) {
            MyTreeNode curr = deque.poll();
            if (curr.level + 1 > ans.size()) {
                ans.add(curr.node.val);
            } else {
                ans.set(curr.level, curr.node.val);
            }
            // 左边
            if (curr.node.left != null) {
                deque.add(new MyTreeNode(curr.node.left, curr.level + 1));
            }
            if (curr.node.right != null) {
                deque.add(new MyTreeNode(curr.node.right, curr.level + 1));
            }
        }
        return ans;
    }
}