package leetcode.editor._110_isBalanced;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */

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

class Solution {
    public boolean isBalanced(TreeNode root) {
        // 计算并比较两边的高度,并比较
        return solve(root) != -1;
    }

    private int solve(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int a = solve(root.left);
        if (a == -1) {
            return -1;
        }
        int b = solve(root.right);
        if (b == -1) {
            return -1;
        }
        if (Math.abs(a - b) > 1) {
            return -1;
        }
        return Math.max(a, b) + 1;
    }
}