package leetcode.editor._909_snakesAndLadders;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] input = new int[][]{
                {-1, -1, 27, 13, -1, 25, -1},
                {-1, -1, -1, -1, -1, -1, -1},
                {44, -1, 8, -1, -1, 2, -1},
                {-1, 30, -1, -1, -1, -1, -1},
                {3, -1, 20, -1, 46, 6, -1},
                {-1, -1, -1, -1, -1, -1, 29},
                {-1, 29, 21, 33, -1, -1, -1}};
        solution.snakesAndLadders(input);
    }
}
