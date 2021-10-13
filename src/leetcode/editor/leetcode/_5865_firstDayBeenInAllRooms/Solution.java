package leetcode.editor._5865_firstDayBeenInAllRooms;

class Solution {
    public int firstDayBeenInAllRooms(int[] nextVisit) {
        // 动态规划?
        // 每个房间到被访问时所花费的最少时间(次数即为时间
        int size = nextVisit.length;
        int[][] dp = new int[size][2];
        dp[0][1] = 1;
        return 0;
    }
}