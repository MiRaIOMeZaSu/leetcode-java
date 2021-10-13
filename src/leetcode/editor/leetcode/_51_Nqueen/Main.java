package leetcode.editor._51_Nqueen;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 5;
        List<List<String>> list = solution.solveNQueens(n);
        for (List<String> i:list) {
            for (String j:i) {
                System.out.println(j);
            }
            System.out.println("----------");
        }
    }
}
