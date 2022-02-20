package leetcode.editor.contest._6014_repeatLimitedString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Node {
    Node(int count, char key) {
        this.count = count;
        this.key = key;
    }

    public int count;
    public char key;
}

class Solution {
    public int ceiling(int a, int b) {
        int ans = a / b;
        if (a % b != 0) {
            ans += 1;
        }
        return ans;
    }

    public String repeatLimitedString(String s, int repeatLimit) {
        // 统计次数
        // 不必使用 s 中的全部字符
        // 首先是越长越好
        // 为保证长度,优先选择数量多的?
        StringBuilder stringBuilder = new StringBuilder();
        char pivot = 'a';
        Node[] keys = new Node[26];
        int size = s.length();
        char[] chars = s.toCharArray();
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (keys[chars[i] - pivot] == null) {
                keys[chars[i] - pivot] = new Node(0, chars[i]);
                list.add(keys[chars[i] - pivot]);
            }
            keys[chars[i] - pivot].count++;
        }
        // 如何排序?
        list.sort((o1, o2) -> {
            // 先看是否超规格
            int need1 = ceiling(o1.count, repeatLimit);
            int need2 = ceiling(o2.count, repeatLimit);
            if (need1 > size - o1.count || need2 > size - o2.count) {
                // 其中一个或两个都超规格
                if (need1 <= size - o1.count) {
                    return -1;
                } else if (need2 <= size - o2.count) {
                    return 1;
                }
            }
            return o1.key - o2.key;
        });
        int border = list.size() - 1;
        while (stringBuilder.length() != size) {
            out:
            for (int j = 0; j < repeatLimit && list.get(border).count > 0; j++) {
                stringBuilder.append(list.get(border).key);
                list.get(border).count--;
            }
            if (list.get(border).count == 0) {
                border--;
            } else {
                // 找一个冤大头作间隔
                boolean end = true;
                for (int i = border - 1; i >= 0; i--) {
                    if (list.get(i).count != 0) {
                        stringBuilder.append(list.get(i).key);
                        list.get(i).count--;
                        end = false;
                        break;
                    }
                }
                if (end) {
                    return stringBuilder.toString();
                }
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.repeatLimitedString("cczazcc", 3));
        System.out.println(solution.repeatLimitedString("aababab", 2));
        System.out.println(solution.repeatLimitedString("aaaaaaab", 2));
    }
}