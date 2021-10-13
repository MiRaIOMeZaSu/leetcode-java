package leetcode.editor._1239_maxLength;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class Solution {
    int[] table = new int[26];

    public Solution() {
        table[0] = 1;
        for (int i = 1; i < 26; i++) {
            table[i] = table[i - 1] * 2;
        }
    }

    public int maxLength(List<String> arr) {
        // 使用简单的位状压
        // 哈希表或者01背包?
        // 由于可以选多个不同的,所以是01背包
        // 或者广度优先!
        HashSet<Integer> visit = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        List<Integer> lens = new ArrayList<>();
        int i = 0;
        int ret = 0;
        int bit, len;
        while (i < arr.size()) {
            bit = getBit(arr.get(i));
            len = arr.get(i).length();
            if (bit != -1) {
                visit.add(bit);
                list.add(bit);
                lens.add(len);
                ret = len;
                i++;
                break;
            }
            i++;
        }
        for (; i < arr.size(); i++) {
            int currSize = list.size();
            bit = getBit(arr.get(i));
            len = arr.get(i).length();
            if (bit == -1) {
                continue;
            }
            if (!visit.contains(bit)) {
                visit.add(bit);
                list.add(bit);
                lens.add(len);
                ret = Math.max(len, ret);
            }
            for (int j = 0; j < currSize; j++) {
                int target = list.get(j);
                if ((target | bit) == target + bit && !visit.contains(target + bit)) {
                    visit.add(target + bit);
                    list.add(target + bit);
                    lens.add(len + lens.get(j));
                    ret = Math.max(len + lens.get(j), ret);
                }
            }
        }
        return ret;
    }

    private int getBit(String string) {
        int bit = 0;
        for (int i = 0; i < string.length(); i++) {
            int temp = table[string.charAt(i) - 'a'];
            if ((bit | temp) == bit) {
                // 存在重复,直接返回
                return -1;
            } else {
                bit |= temp;
            }
        }
        return bit;
    }
}