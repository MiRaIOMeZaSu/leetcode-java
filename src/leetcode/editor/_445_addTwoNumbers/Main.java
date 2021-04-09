package leetcode.editor._445_addTwoNumbers;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int [][]matrix = new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        int ret = solution.kthSmallest(matrix,8);
        System.out.println(ret);
    }
}
