package leetcode.editor._437_pathSum;

import java.util.HashMap;
import java.util.Iterator;

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
        ans += solve(root).getOrDefault(targetSum,0);
        return ans;
    }

    private HashMap<Integer, Integer> solve(TreeNode node) {
        if (node == null) {
            return new HashMap<>();
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        if (node.left != null) {
            HashMap<Integer, Integer> map1 = solve(node.left);
            ans += map1.getOrDefault(targetSum, 0);
            Iterator<Integer> integerIterator = map1.keySet().iterator();
            while (integerIterator.hasNext()) {
                int key = integerIterator.next();
                map.merge(key + node.val, map1.get(key), Integer::sum);
            }
        }
        if (node.right != null) {
            HashMap<Integer, Integer> map2 = solve(node.right);
            ans += map2.getOrDefault(targetSum, 0);
            Iterator<Integer> integerIterator = map2.keySet().iterator();
            while (integerIterator.hasNext()) {
                int key = integerIterator.next();
                map.merge(key + node.val, map2.get(key), Integer::sum);
            }
        }
        map.merge(node.val, 1, Integer::sum);
        return map;
    }
}