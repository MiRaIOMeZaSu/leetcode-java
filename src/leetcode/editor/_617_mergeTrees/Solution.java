package leetcode.editor._617_mergeTrees;

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
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // 同时遍历两颗树
        if (root1 == null || root2 == null) {
            return root1 == null ? root2 : root1;
        }
        TreeNode ans = new TreeNode(root1.val + root2.val);
        solve(root1, root2, ans);
        return ans;
    }

    private void solve(TreeNode root1, TreeNode root2, TreeNode ans) {
        // 左边
        if (root1.left == null || root2.left == null) {
            ans.left = root1.left == null ? root2.left : root1.left;
        } else {
            ans.left = new TreeNode(root1.left.val + root2.left.val);
            solve(root1.left, root2.left, ans.left);
        }
        if (root1.right == null || root2.right == null) {
            ans.right = root1.right == null ? root2.right : root1.right;
        } else {
            ans.right = new TreeNode(root1.right.val + root2.right.val);
            solve(root1.right, root2.right, ans.right);
        }
    }
}