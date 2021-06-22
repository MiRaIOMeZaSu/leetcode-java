package leetcode.editor.offer._33_verifyPostorder;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    int index;
    int[] nums;

    public boolean verifyPostorder(int[] postorder) {
        // 从后往前尝试构造二叉搜索树
        // 数字都不同
        // 深度优先
        int size = postorder.length;
        if (size == 0) {
            return true;
        }
        nums = postorder;
        TreeNode root = new TreeNode(postorder[size - 1]);
        index = size - 2;
        solve(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (index < 0) {
            return true;
        }
        return false;
    }

    private void solve(TreeNode node, int minLimit, int maxLimit) {
        if (index < 0) {
            return;
        }
        if (nums[index] > maxLimit || nums[index] < minLimit) {
            return;
        }
        if (node.val < nums[index] && node.right == null) {
            node.right = new TreeNode(nums[index]);
            index--;
            solve(node.right, Math.max(node.val, minLimit), maxLimit);
        }
        if (index < 0) {
            return;
        }
        if (nums[index] > maxLimit || nums[index] < minLimit) {
            return;
        }
        if (node.val > nums[index] && node.left == null) {
            node.left = new TreeNode(nums[index]);
            index--;
            solve(node.left, minLimit, Math.min(node.val, maxLimit));
        }
    }
}