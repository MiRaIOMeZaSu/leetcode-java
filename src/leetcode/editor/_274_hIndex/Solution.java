package leetcode.editor._274_hIndex;

import java.util.Arrays;

class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int size = citations.length;
        int res = 0;
        if (citations[0]>=size){
            return size;
        }
        for (int i = size - 1; i > 0; i--) {
            int count = size - i;
            if (citations[i] >= count && citations[i - 1] <= count) {
                res = count;
            }
        }
        return res;
    }
}