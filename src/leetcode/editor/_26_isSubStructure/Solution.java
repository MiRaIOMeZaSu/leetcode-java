package leetcode.editor._26_isSubStructure;

import com.sun.source.tree.Tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

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
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        // 先广度优先再深度优先?
        // 可能存在相同值的结点
        Deque<TreeNode> q = new LinkedList<>();
        q.add(A);
        while (!q.isEmpty()) {
            TreeNode n = q.poll();
            if (solve(n, B)) {
                return true;
            }
            if (n.left != null) {
                q.add(n.left);
            }
            if (n.right != null) {
                q.add(n.right);
            }
        }
        return false;
    }

    private boolean solve(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            if (B == null) {
                return true;
            } else {
                return false;
            }
        }
        if (A.val != B.val) {
            return false;
        }
        if (solve(A.left, B.left) && solve(A.right, B.right)) {
            return true;
        }
        return false;
    }
}