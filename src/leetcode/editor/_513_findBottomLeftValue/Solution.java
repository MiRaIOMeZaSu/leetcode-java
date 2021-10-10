package leetcode.editor._513_findBottomLeftValue;

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
    TreeNode ans = null;
    int level = -1;
    public int findBottomLeftValue(TreeNode root) {
        // 首先是最底层,直接使用深度优先中序遍历即可
        // 结点数量大于1
        ans = root;
        solve(root, 0);
        return ans.val;
    }

    private void solve(TreeNode node, int level) {
        if(level > this.level){
            ans = node;
            this.level = level;
        }
        if (node.left != null) {
            solve(node.left, level + 1);
        }
        if (node.right != null) {
            solve(node.right, level + 1);
        }
    }
}