package leetcode.editor._144_preorderTraversal;

import com.sun.source.tree.Tree;

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

class Solution {
    List<Integer> ans = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        // 简单的前序遍历
        if (root != null) {
            solve(root);
        }
        return ans;
    }

    private void solve(TreeNode node) {
        ans.add(node.val);
        if (node.left != null) {
            solve(node.left);
        }
        if (node.right != null) {
            solve(node.right);
        }
    }
}
