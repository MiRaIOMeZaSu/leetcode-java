package leetcode.editor._49_groupAnagrams;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] in = new String[]
                {"bdddddddddd", "bbbbbbbbbbc"};
        List<List<String>> ret = solution.groupAnagrams(in);
        System.out.println(ret);
    }
}
