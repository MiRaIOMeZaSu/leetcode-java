package leetcode.editor.offer._37_Codec;


public class Main {
    public static void main(String[] args) {
        Codec codec = new Codec();
        TreeNode root = new TreeNode(1);
        TreeNode _1 = new TreeNode(2);
        TreeNode _2 = new TreeNode(3);
        root.left = _1;
        root.right = _2;
        TreeNode _3 = new TreeNode(4);
        TreeNode _4 = new TreeNode(5);
        _2.left = _3;
        _2.right = _4;
        codec.deserialize(codec.serialize(root));
    }
}
