package leetcode.editor._327_countRangeSum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

class Tree {
    int treeArr[];
    int size;
    int count;

    Tree(int count) {
        this.count = count;
        size = count;
        // 懒得考虑是2倍还是4倍
        size *= 4;
        treeArr = new int[size];
    }

    int getRangeCount(int currStart, int currEnd, int targetStart, int targetEnd, int k) {
        if (currStart >= targetStart && currEnd <= targetEnd) {
            return treeArr[k];
        } else if (currStart > targetEnd || currEnd < targetStart) {
            return 0;
        }
        int div = (currStart + currEnd) >> 1;
        int a = getRangeCount(currStart, div, targetStart, targetEnd, k * 2 + 1);
        int b = getRangeCount(div + 1, currEnd, targetStart, targetEnd, k * 2 + 2);
        return a + b;
    }

    void _addNum(int currStart, int currEnd, int num, int k) {
        if (num > currEnd || num < currStart) {
            return;
        }
        treeArr[k] += 1;
        if (currStart == currEnd) {
            return;
        }
        int div = (currStart + currEnd) >> 1;
        _addNum(currStart, div, num, k * 2 + 1);
        _addNum(div + 1, currEnd, num, k * 2 + 2);
    }

    void addNum(int num) {
        _addNum(0, count, num, 0);
    }
}

class SpecialTree extends Tree {
    HashMap<Integer, Integer> map;

    SpecialTree(int count, HashMap<Integer, Integer> map) {
        super(count);
        this.map = map;
    }

    int _getRangeCountSpecial(int currStart, int currEnd, int targetStart, int targetEnd, int k) {
        currStart = map.get(currStart);
        currEnd = map.get(currEnd);
        targetStart = map.get(targetStart);
        targetEnd = map.get(targetEnd);
        return getRangeCount(currStart, currEnd, targetStart, targetEnd, k);
    }

    int getRangeCountSpecial(int targetStart, int targetEnd) {
        targetStart = map.get(targetStart);
        targetEnd = map.get(targetEnd);
        return getRangeCount(0, count, targetStart, targetEnd, 0);
    }

    int getLowerCount(int targetEnd) {
        targetEnd = map.get(targetEnd) - 1;
        return getRangeCount(0, count, 0, targetEnd, 0);
    }

    int getHigherCount(int targetStart) {
        targetStart = map.get(targetStart) + 1;
        return getRangeCount(0, count, targetStart, count, 0);
    }

    void addNumSpecial(int num) {
        num = map.get(num);
        _addNum(0, count, num, 0);
    }
}

class Solution {
    int lower, upper;

    public int countRangeSum(int[] nums, int lower, int upper) {
        this.upper = upper;
        this.lower = lower;
        // 遍历区间?
        // 动态规划?
        // 前缀和? - 前缀与后缀!(线段树!)
        // 滑动窗口?
        // 线段树?
        int size = nums.length;
        int[] pres = new int[size];
        int[] tail = new int[size];
        pres[0] = nums[0];
        tail[size - 1] = nums[size - 1];
        for (int i = 1; i < size; i++) {
            pres[i] = pres[i - 1] + nums[i];
            tail[size - i - 1] = tail[size - i] + nums[size - i - 1];
        }
        int total = pres[size - 1];
        // 使用线段树!(nums[i]的范围十分巨大)
        // 使用特殊线段树
        List<Integer> arr = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] diff = new int[]{0, lower, upper};
        for (int i = 0; i < pres.length; i++) {
            for (int j = 0; j < 3; j++) {
                int key = pres[i] - diff[j];
                if (!map.containsKey(key)) {
                    map.put(key, 0);
                    arr.add(key);
                }
            }
        }
        arr.sort(Comparator.naturalOrder());
        for (int i = 0; i < arr.size(); i++) {
            map.put(arr.get(i), i);
        }
        SpecialTree tree = new SpecialTree(arr.size() + 1, map);
        int ans = 0;
        for (int i = 2; i < size; i++) {
            tree.addNumSpecial(pres[i - 2]);
            int temp = total - tail[i];
            int left = temp - upper;
            int right = temp - lower;
            ans += tree.getRangeCountSpecial(left, right);
        }
        for (int i = 0; i < size - 1; i++) {
            if (satisfy(pres[i])) {
                ans++;
            }
            if (satisfy(tail[i + 1])) {
                ans++;
            }
        }
        if (satisfy(total)) {
            ans++;
        }
        return ans;
    }

    private boolean satisfy(int a) {
        return a <= upper && a >= lower;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.countRangeSum(new int[]{-2, 5, -1}, -2, 2);
    }
}