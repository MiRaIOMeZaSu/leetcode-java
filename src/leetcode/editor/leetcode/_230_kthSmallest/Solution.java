package leetcode.editor._230_kthSmallest;

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
    int index = 0;
    boolean flag = false;
    int k;
    int ans = -1;

    public int kthSmallest(TreeNode root, int k) {
        // 二叉搜索树左小右大,遍历即可
        this.k = k;
        solve(root);
        return ans;
    }

    private void solve(TreeNode node) {
        // 中序遍历
        if (node.left != null) {
            solve(node.left);
        } else {
            // 此时可以开始计数
            flag = true;
        }
        if (flag) {
            index++;
        }
        if (index == k) {
            ans = node.val;
        }
        if (ans != -1) {
            return;
        }
        if (node.right != null) {
            solve(node.right);
        }
    }
}