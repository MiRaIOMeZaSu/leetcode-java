package leetcode.editor._235_lowestCommonAncestor;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private TreeNode ret;
    private int[] targets = new int[2];

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 必须是广度优先?
        // 不是深度优先的话无法获取父节点?
        targets[0] = p.val;
        targets[1] = q.val;
        Arrays.sort(targets);
        solve(root);
        return ret;
    }

    private int solve(TreeNode node) {
        // 找到时返回-1
        int k = 0;
        if (node == null) {
            return 0;
        }
        if (node.val == targets[0]) {
            k += 1;
        } else if (node.val == targets[1]) {
            k += 2;
        }
        if (node.val > targets[0]) {
            int temp = solve(node.left);
            if (temp == -1) {
                return temp;
            }
            k += temp;
        }
        if (k == 3) {
            ret = node;
            return -1;
        }
        if (node.val < targets[1]) {
            int temp = solve(node.right);
            if (temp == -1) {
                return temp;
            }
            k += temp;
        }
        if (k == 3) {
            ret = node;
            return -1;
        }
        return k;
    }
}