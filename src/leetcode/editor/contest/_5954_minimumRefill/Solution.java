package leetcode.editor.contest._5954_minimumRefill;

class Solution {
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        // 返回两人需要的灌水次数的和
        int ans = 0;
        int size = plants.length;
        // 灌水对速度无影响
        int currCap = capacityA;
        for (int i = 0; i < (size >> 1); i++) {
            if (currCap >= plants[i]) {
                currCap -= plants[i];
            } else {
                currCap = capacityA - plants[i];
                ans++;
            }
        }
        int restA = currCap;
        currCap = capacityB;
        int rightBorder = size >> 1;
        if (size % 2 == 0) {
            rightBorder--;
        }
        for (int i = size - 1; i > rightBorder; i--) {
            if (currCap >= plants[i]) {
                currCap -= plants[i];
            } else {
                currCap = capacityB - plants[i];
                ans++;
            }
        }
        if (size % 2 != 0 && plants[(size >> 1)] > Math.max(restA, currCap)) {
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.minimumRefill(new int[]{1, 2, 4, 4, 5}, 6, 5);
    }
}