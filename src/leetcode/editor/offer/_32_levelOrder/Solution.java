package leetcode.editor.offer._32_levelOrder;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
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
    public int[] levelOrder(TreeNode root) {
        Deque<TreeNode> q = new LinkedList<>();
        List<Integer> ret = new ArrayList<>();
        if (root != null) {
            q.push(root);
        }
        while (!q.isEmpty()) {
            TreeNode n = q.pollFirst();
            ret.add(n.val);
            if (n.left != null) {
                q.push(n.left);
            }
            if (n.right != null) {
                q.push(n.right);
            }
        }
        int size = ret.size();
        int[] retArr = new int[size];
        for (int i = 0; i < size; i++) {
            retArr[i] = ret.get(i);
        }
        return retArr;
    }
}