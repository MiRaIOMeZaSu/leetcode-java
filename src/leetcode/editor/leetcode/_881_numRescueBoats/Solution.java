package leetcode.editor._881_numRescueBoats;

import java.util.Arrays;

class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int size = people.length;
        int left = 0;
        int right = size - 1;
        int result = 0;
        while (left < right) {
            if (people[left] + people[right] <= limit) {
                result++;
                left++;
                right--;
            } else {
                right--;
                result++;
            }
        }
        return result + (left == right ? 1 : 0);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.numRescueBoats(new int[]{3, 2, 2, 1}, 3);
    }
}