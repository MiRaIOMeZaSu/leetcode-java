package leetcode.editor._938_rangeSumBST;


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
    public int rangeSumBST(TreeNode root, int low, int high) {
        int ret = 0;
        if (root != null) {
            if (root.val >= low && root.val <= high) {
                ret += root.val;
                if (root.left != null) {
                    ret += rangeSumBST(root.left, low, high);
                }
                if (root.right != null) {
                    ret += rangeSumBST(root.right, low, high);
                }
            } else if (root.val < low && root.right != null) {
                ret += rangeSumBST(root.right, low, high);
            } else if (root.val > high && root.left != null) {
                ret += rangeSumBST(root.left, low, high);
            }
        }
        return ret;
    }
}