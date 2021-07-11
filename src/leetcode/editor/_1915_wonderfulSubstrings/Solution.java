package leetcode.editor._1915_wonderfulSubstrings;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public long wonderfulSubstrings(String word) {
        // 寻找子字符串
        // 窗口?
        // 将奇偶映射成32位整数
        int size = word.length();
        int range = 'j' - 'a' + 1;
        int[] bits = new int[range];
        bits[0] = 1;
        int mask = 1;
        for (int i = 1; i < range; i++) {
            bits[i] = bits[i - 1] * 2;
            mask |= bits[i];
        }
        int preBit = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int[] tail = new int[size];
        int res = 0;
        for (int i = size - 1; i >= 0; i--) {
            tail[i] = preBit ^ bits[word.charAt(i) - 'a'];
            preBit = tail[i];
            if (is2(preBit)) {
                res += 1;
            }
            map.put(preBit, map.getOrDefault(preBit, 0) + 1);
        }
        mapMinus(map, preBit);
        preBit = 0;
        for (int i = 0; i < size - 1; i++) {
            mapMinus(map, tail[i + 1]);
            preBit = preBit ^ bits[word.charAt(i) - 'a'];
            if (is2(preBit)) {
                res += 1;
            }
            // 开始寻找
            for (int j = 0; j < range; j++) {
                int target1 = bits[j] ^ tail[0] ^ preBit;
                res += map.getOrDefault(target1, 0);
            }
            res += map.getOrDefault(tail[0] ^ preBit, 0);
        }
        return res;
    }

    void mapMinus(Map<Integer, Integer> map, int key) {
        int num = map.get(key);
        if (num == 1) {
            map.remove(key);
        } else {
            map.put(key, num - 1);
        }
    }

    boolean is2(int num) {
        return (num & (num - 1)) == 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.wonderfulSubstrings("aabb");
    }
}