package leetcode.editor._275_hIndex;

class Solution {
    public int hIndex(int[] citations) {
        // 二分法?
        int size = citations.length;
        int res = 0;
        if (citations[0] >= size) {
            return size;
        }
        int left = 0;
        int right = size - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            int count = size - mid;
            if (citations[mid] >= count && citations[mid - 1] <= count) {
                right = mid;
            }
        }
        return size - right;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.hIndex(new int[]{0, 1, 3, 5, 6, 6, 6, 6, 6, 9, 10, 11, 15, 16, 23});
    }
}