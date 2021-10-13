package leetcode.editor._124_maxPathSum;

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
class Solution {
    private int ret = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        // 使用中序遍历
        solve(root);
        return ret;
    }

    public int solve(TreeNode root) {
        int left = 0;
        int right = 0;
        if (root.left != null) {
            left = solve(root.left);
        }
        if (root.right != null) {
            right = solve(root.right);
        }
        int temp = root.val;
        int toAdd = 0;
        if (left > 0) {
            temp += left;
            toAdd = Math.max(toAdd, left);
        }
        if (right > 0) {
            temp += right;
            toAdd = Math.max(toAdd, right);
        }
        ret = Math.max(temp, ret);
        return root.val + toAdd;
    }
}