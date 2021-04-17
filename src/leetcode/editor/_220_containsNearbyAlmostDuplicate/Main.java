package leetcode.editor._220_containsNearbyAlmostDuplicate;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{7, 1, 3};
        boolean ret = solution.containsNearbyAlmostDuplicate(arr, 2, 3);
        System.out.println(ret);
    }
}
