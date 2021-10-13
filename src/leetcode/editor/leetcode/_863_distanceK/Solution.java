package leetcode.editor._863_distanceK;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int k;
    int target;
    List<Integer> result = new ArrayList<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // 回溯法?
        this.target = target.val;
        this.k = k;
        return result;
    }

    private int solve(int distance, TreeNode node) {
        if (distance == target) {
            result.add(node.val);
            return target;
        }
        int a = distance;
        int b = distance;
        if (node.left != null) {
            a = solve(distance, node.left);
        }
        if (node.right != null) {
            b = solve(distance, node.right);
        }
        int temp = Math.min(a, b) - 1;
        if (temp == k) {
            result.add(node.val);
        }
        return -1;
    }
}