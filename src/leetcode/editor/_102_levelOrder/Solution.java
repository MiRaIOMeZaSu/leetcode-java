package leetcode.editor._102_levelOrder;

import java.util.ArrayList;
import java.util.List;

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
    List<List<Integer>> ret;

    public List<List<Integer>> levelOrder(TreeNode root) {
        ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        List<TreeNode> base = new ArrayList<>();
        List<TreeNode> nodes = new ArrayList<>();
        base.add(root);
        while (!base.isEmpty()) {
            List<Integer> item = new ArrayList<>();
            for (TreeNode node : base) {
                item.add(node.val);
                if(node.left!=null){
                    nodes.add(node.left);
                }
                if(node.right!=null){
                    nodes.add(node.right);
                }
            }
            ret.add(item);
            List<TreeNode> temp;
            temp = base;
            base = nodes;
            nodes = temp;
            nodes.clear();
        }
        return ret;
    }
}