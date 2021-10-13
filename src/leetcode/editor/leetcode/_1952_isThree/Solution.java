package leetcode.editor._1952_isThree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public boolean isThree(int n) {
        // 恰好有3个除数
        // 只能刚好有一个小于n且大于1的除数
        int pivot = 0;
        for (int i = 2; i < n && pivot < 2; i++) {
            if (n % i == 0) {
                pivot++;
                int temp = i * i;
                if (n % temp == 0&&temp!=n) {
                    return false;
                }
            }
        }
        if (pivot == 0) {
            // 只有两个除数
            return false;
        }
        if (pivot > 1) {
            return false;
        }
        return true;
    }
}