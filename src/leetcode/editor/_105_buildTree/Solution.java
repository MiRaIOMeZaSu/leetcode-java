package leetcode.editor._105_buildTree;

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
    private int index = 0;
    private int[] pre;
    private int[] in;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 分治法/归并法
        int size = inorder.length;
        pre = preorder;
        in = inorder;
        TreeNode root = new TreeNode(preorder[index]);
        index++;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == preorder[index - 1]) {
                solve(0, i - 1, root, true);
                solve(i + 1, size - 1, root, false);
                break;
            }
        }

        return root;
    }

    private void solve(int left, int right, TreeNode root, boolean isLeft) {
        if (left > right) {
            return;
        }
        index++;
//        if (index - 1 >= pre.length) {
//            return;
//        }
        TreeNode node = new TreeNode(pre[index - 1]);
        if (isLeft) {
            root.left = node;
        } else {
            root.right = node;
        }
        if (left == right) {
            return;
        }
        for (int i = left; i <= right; i++) {
            if (in[i] == pre[index - 1]) {
                solve(left, i - 1, node, true);
                solve(i + 1, right, node, false);
                break;
            }
        }
    }
}