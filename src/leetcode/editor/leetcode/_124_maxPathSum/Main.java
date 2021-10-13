package leetcode.editor._124_maxPathSum;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode _2 = new TreeNode(4);
        TreeNode _3 = new TreeNode(8);
        TreeNode _4 = new TreeNode(11);
        TreeNode _5 = new TreeNode(13);
        TreeNode _6 = new TreeNode(4);
        TreeNode _7 = new TreeNode(7);
        TreeNode _8 = new TreeNode(2);
        TreeNode _9 = new TreeNode(1);
        root.left = _2;
        root.right = _3;
        _2.left = _4;
        _4.left = _7;
        _4.right = _8;
        _3.left = _5;
        _3.right = _6;
        _6.right = _9;
        Solution solution = new Solution();
        int ret = solution.maxPathSum(root);
        System.out.println(ret);
    }
}
