package leetcode.editor.contest._6018_createBinaryTree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
    public TreeNode createBinaryTree(int[][] descriptions) {

        Set<Integer> hasParents = new HashSet<>();
        HashMap<Integer, TreeNode> treeNodes = new HashMap<>(2 * descriptions.length);
        // 如何构造?
        for (int i = 0; i < descriptions.length; i++) {
            int[] description = descriptions[i];
            for (int j = 0; j < 2; j++) {
                if (treeNodes.get(description[j]) == null) {
                    treeNodes.put(description[j], new TreeNode(description[j]));
                }
            }
            if (description[2] == 1) {
                // 是左边
                treeNodes.get(description[0]).left = treeNodes.get(description[1]);
            } else {
                treeNodes.get(description[0]).right = treeNodes.get(description[1]);
            }
            hasParents.add(description[1]);
        }
        for (int val :
                treeNodes.keySet()) {
            if (!hasParents.contains(val)) {
                return treeNodes.get(val);
            }
        }
        return null;
    }
}