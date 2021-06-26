package leetcode.editor.interview._0409_BSTSequences;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(2);
        TreeNode _1 = new TreeNode(1);
        TreeNode _2 = new TreeNode(3);
        TreeNode _3 = new TreeNode(0);
        TreeNode _4 = new TreeNode(9);
        _1.left = _3;
        _2.right = _4;
        root.left = _1;
        root.right = _2;
        solution.BSTSequences(root);
    }
}
