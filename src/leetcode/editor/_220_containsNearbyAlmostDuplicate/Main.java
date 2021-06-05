package leetcode.editor._220_containsNearbyAlmostDuplicate;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{2147483646,2147483647};
        boolean ret = solution.containsNearbyAlmostDuplicate(arr, 3, 3);
        System.out.println(ret);
    }
}
