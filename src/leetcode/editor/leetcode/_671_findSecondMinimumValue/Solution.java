package leetcode.editor._671_findSecondMinimumValue;

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
    public int findSecondMinimumValue(TreeNode root) {
        // 往左找到第一个较小值,往右找到第一个较小值
        int result = -1;
        if (root.left != null) {
            if (root.left.val != root.val) {
                result = root.left.val;
            } else {
                result = findSecondMinimumValue(root.left);
            }
        }
        if (root.right != null) {
            int temp;
            if (root.right.val != root.val) {
                temp = root.right.val;
            } else {
                temp = findSecondMinimumValue(root.right);
            }
            if (temp == -1) {
                return result;
            }
            if (result == -1) {
                return temp;
            } else {
                return Math.min(temp, result);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode _1 = new TreeNode(8);
        TreeNode _2 = new TreeNode(5);
        root.left = _1;
        root.right = _2;
        Solution solution = new Solution();
        solution.findSecondMinimumValue(root);
    }
}