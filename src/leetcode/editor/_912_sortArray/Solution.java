package leetcode.editor._912_sortArray;

class Solution {
    public int[] sortArray(int[] nums) {
        /*
         * 所有排序的方法
         * 快速排序 : 分区法
         * 冒泡排序 : 依次遍历法
         * 堆排序 : 使用二叉堆
         * 希尔排序 : 特殊的插入排序,进行插入排序的序列越来越少,越来越长
         * 桶排序 : 区间粗排序
         * 插入排序 : 依次插入法
         * 选择排序 : ?
         * 归并排序 : 分区排序再合并,归并法
         * 数位法 : 按照位数依次归纳,辅助比较
         * */
        // 使用快排,原地排序
        patition(nums, 0, nums.length - 1);
        return nums;
    }

    private void patition(int[] nums, int leftBorder, int rightBorder) {
        if (rightBorder - leftBorder < 1) {
            return;
        }
        int pivot = nums[leftBorder];
        int left = leftBorder + 1;
        int right = rightBorder;
        int currIndex = left;
        while (left <= right && currIndex <= right) {
            if (nums[currIndex] < pivot) {
                exchange(nums, left, currIndex);
                left++;
            } else {
                exchange(nums, right, currIndex);
                right--;
                continue;
            }
            currIndex++;
        }
        exchange(nums, leftBorder, left - 1);
        patition(nums, leftBorder, left - 2);
        patition(nums, left, rightBorder);
    }

    private void exchange(int[] nums, int indexA, int indexB) {
        if (indexA == indexB) {
            return;
        }
        int temp = nums[indexA];
        nums[indexA] = nums[indexB];
        nums[indexB] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.sortArray(new int[]{67, 57, 19, 94, 70, 68, 8, 32, 68});
    }
}