package leetcode.editor._437_pathSum;

import java.util.HashMap;

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
    HashMap<Integer, Integer> map = new HashMap<>();
    int ans = 0;
    int targetSum;

    public int pathSum(TreeNode root, int targetSum) {
        // 使用回溯法
        this.targetSum = targetSum;
        map.put(0, 1);
        solve(root);
        return ans;
    }

    private void solve(TreeNode node) {
        int targetKey = targetSum - node.val;
        ans += map.getOrDefault(targetKey, 0);
        map.merge(node.val, 1, Integer::sum);
        if (node.left != null) {
            solve(node.left);
        }
        if (node.right != null) {
            solve(node.right);
        }
        int curr = map.merge(node.val, -1, Integer::sum);
        if (curr == 0) {
            map.remove(node.val);
        }
    }
}