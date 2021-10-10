package leetcode.editor.offer._32_levelOrderIII;


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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        Deque<TreeNode> curr = new LinkedList<>();
        Deque<TreeNode> next = new LinkedList<>();
        Deque<TreeNode> temp;
        if (root != null) {
            curr.add(root);
        }
        boolean flag = true;
        while (!curr.isEmpty() || !next.isEmpty()) {
            List<Integer> localRet = new ArrayList<>();
            if (flag) {
                while (!curr.isEmpty()) {
                    TreeNode n = curr.poll();
                    localRet.add(n.val);
                    if (n.left != null) {
                        next.push(n.left);
                    }
                    if (n.right != null) {
                        next.push(n.right);
                    }
                }
            } else {
                while (!curr.isEmpty()) {
                    TreeNode n = curr.poll();
                    localRet.add(n.val);
                    if (n.right != null) {
                        next.push(n.right);
                    }
                    if (n.left != null) {
                        next.push(n.left);
                    }
                }
            }
            ret.add(localRet);
            flag = !flag;
            temp = curr;
            curr = next;
            next = temp;
        }
        return ret;
    }
}