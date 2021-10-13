package leetcode.editor._1109_corpFlightBookings;

class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] handle = new int[n + 2];
        int size = bookings.length;
        for (int i = 0; i < size; i++) {
            handle[bookings[i][0]] += bookings[i][2];
            handle[bookings[i][1] + 1] -= bookings[i][2];
        }
        int[] result = new int[n];
        int curr = 0;
        for (int i = 0; i < n; i++) {
            curr += handle[i + 1];
            result[i] = curr;
        }
        return result;
    }
}