package leetcode.editor._47_permuteUnique;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] a = new int[]{1, 2,3};
        Solution solution = new Solution();
        List<List<Integer>> ret = solution.permuteUnique(a);
        System.out.println(ret);
    }
}
