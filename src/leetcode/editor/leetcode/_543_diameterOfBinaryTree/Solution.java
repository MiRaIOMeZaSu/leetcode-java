package leetcode.editor.leetcode._543_diameterOfBinaryTree;

import com.sun.source.tree.Tree;

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
    int ans = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        // 后序遍历!
        // 使用后序遍历
        solve(root);
        return ans - 1;
    }

    private int solve(TreeNode node) {
        int l = 0;
        int r = 0;
        if (node.left != null) {
            l = solve(node.left);
        }
        if (node.right != null) {
            r = solve(node.right);
        }
        ans = Math.max(l + r + 1, ans);
        return Math.max(r, l) + 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        //            1
        //            / \
        //            2   3
        //            / \
        //            4   5
        TreeNode root = new TreeNode(1);
        TreeNode _2 = new TreeNode(2);
        TreeNode _3 = new TreeNode(3);
        TreeNode _4 = new TreeNode(4);
        TreeNode _5 = new TreeNode(5);
        root.left = _2;
        root.right = _3;
        _2.left = _4;
        _2.right = _5;
        solution.diameterOfBinaryTree(root);
    }
}