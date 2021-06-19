package leetcode.editor.offer._54_kthLargest;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    private int index = 0;
    private int target;

    public int kthLargest(TreeNode root, int k) {
        target = k;
        // 以右边为起始的中序遍历
        return (int) solve(root);
    }

    private Number solve(TreeNode node) {
        if (node.right != null) {
            Number temp = solve(node.right);
            if (temp != null) {
                return temp;
            }
        }
        index += 1;
        if (index == target) {
            return node.val;
        }
        if (node.left != null) {
            Number temp = solve(node.left);
            if (temp != null) {
                return temp;
            }
        }
        return null;
    }
}