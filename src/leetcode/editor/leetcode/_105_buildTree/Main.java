package leetcode.editor._105_buildTree;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] pre = new int[]{1,2,3,4};
        int[] in = new int[]{1,2,3,4};
        solution.buildTree(pre, in);
    }
}
