package leetcode.editor.leetcode._112_hasPathSum;

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
    boolean ans = false;
    int target;

    public boolean hasPathSum(TreeNode root, int targetSum) {
        // 简单的回溯法
        if (root == null) {
            return false;
        }
        target = targetSum;
        solve(root, 0);
        return ans;
    }

    private void solve(TreeNode node, int curr) {
        if (ans) {
            return;
        }
        curr += node.val;
        byte bit = 0;
        if (node.left != null) {
            bit |= 1;
            solve(node.left, curr);
        }
        if (node.right != null) {
            bit |= 2;
            solve(node.right, curr);
        }
        if (bit == 0 && curr == target) {
            ans = true;
            return;
        }
    }
}