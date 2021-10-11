package leetcode.editor._754_reachNumber;

class Solution {
    public int reachNumber(int target) {
        // 使用(哈希链表)背包法
        target = Math.abs(target);
        int index = (int) Math.sqrt(target * 2);
        while (true) {
            int sum = (index + 1) * index / 2;
            int left = 2;
            int right = sum * 2;
            int twoNum = sum + target;
            if (twoNum % 2 == 0 && twoNum >= left && twoNum <= right) {
                return index;
            }
            index++;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.reachNumber(2);
    }
}