package leetcode.editor.leetcode._257_binaryTreePaths;

import java.util.ArrayList;
import java.util.List;

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
    List<String> ans = new ArrayList<>();
    List<Integer> stack = new ArrayList<>();
    final static String p = "->";

    public List<String> binaryTreePaths(TreeNode root) {
        stack.add(root.val);
        if (root.left != null) {
            solve(root.left);
        }
        if (root.right != null) {
            solve(root.right);
        }
        if (root.left == null && root.right == null) {
            ans.add(String.valueOf(root.val));
        }
        return ans;
    }

    private void solve(TreeNode node) {
        stack.add(node.val);
        byte bit = 0;
        if (node.left != null) {
            bit |= 1;
            solve(node.left);
        }
        if (node.right != null) {
            bit |= 2;
            solve(node.right);
        }
        int size = stack.size();
        if (bit == 0) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(stack.get(0));
            for (int i = 1; i < size; i++) {
                stringBuffer.append(p);
                stringBuffer.append(stack.get(i));
            }
            ans.add(stringBuffer.toString());
        }
        stack.remove(size - 1);
    }
}