package leetcode.editor._493_reversePairs;

import java.util.*;

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
    public int reversePairs(int[] nums) {
        // 往后存且一边存一遍计算即可,此时必然满足i < j
        // 数字的大小不限!
        // 数组长度最长为50000
        // 实际是可以实现线段树的(需要十万空间
        // 应该直接获取范围而避免一个一个加和!
        // 前往后依次构造树并查询

        List<Integer> arr = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            long bigNum = num;
            int pivot = 3;
            if (bigNum * 2 > Integer.MAX_VALUE || bigNum * 2 < Integer.MIN_VALUE) {
                pivot--;
            }
            // 此处处理溢出
            for (int j = 1; j < pivot; j++) {
                int key = num * j;
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
        tree.addNumSpecial(nums[0]);
        int ret = 0;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            long bigNum = num;
            if (bigNum * 2 < Integer.MIN_VALUE) {
                // 负数溢出
                ret+=i;
            } else if (bigNum * 2 < Integer.MAX_VALUE) {
                ret += tree.getHigherCount(num * 2);
            }
            tree.addNumSpecial(num);
        }
        return ret;
    }
}