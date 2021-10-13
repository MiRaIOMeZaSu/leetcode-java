package leetcode.editor._43_searchRange;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {};
        int target = 0;
        int[] result = solution.searchRange(nums, target);
        for (int i : result) {
            System.out.print(i);
        }
    }
}
