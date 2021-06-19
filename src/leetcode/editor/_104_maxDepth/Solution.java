package leetcode.editor._104_maxDepth;

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
    private int ret = 0;

    public int maxDepth(TreeNode root) {
        // 直接使用深度优先
        solve(root, 0);
        return ret;
    }

    private void solve(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        depth += 1;
        ret = Math.max(depth, ret);
        solve(root.left, depth);
        solve(root.right, depth);
    }
}