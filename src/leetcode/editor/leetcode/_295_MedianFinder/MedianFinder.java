package leetcode.editor._295_MedianFinder;

import java.util.Comparator;
import java.util.TreeMap;

class MedianFinder {

    /**
     * initialize your data structure here.
     */
    TreeMap<Integer, Integer> left = new TreeMap<>((o1, o2) -> o2 - o1);
    TreeMap<Integer, Integer> right = new TreeMap<>();
    int leftCount = 0;
    int rightCount = 0;

    public MedianFinder() {
        // 二叉搜索树+链表
        // 两个单调栈?
        // 两颗树!
    }

    public void addNum(int num) {
        if (leftCount == rightCount) {
            leftCount++;
            if (leftCount == 1) {
                left.put(num, left.getOrDefault(num, 0) + 1);
                return;
            }
            int leftKey = left.firstKey();
            if (num <= leftKey) {
                left.put(num, left.getOrDefault(num, 0) + 1);
            } else {
                if (num <= right.firstKey()) {
                    left.put(num, left.getOrDefault(num, 0) + 1);
                    return;
                }
                right.put(right.firstKey(), right.firstEntry().getValue() - 1);
                left.put(right.firstKey(), left.getOrDefault(right.firstKey(), 0) + 1);
                if (right.firstEntry().getValue() == 0) {
                    right.pollFirstEntry();
                }
                right.put(num, right.getOrDefault(num, 0) + 1);
            }
        } else {
            rightCount++;
            if (leftCount == 1 && num < left.firstKey()) {
                right.put(left.firstKey(), 1);
                left.remove(left.firstKey());
                left.put(num, 1);
                return;
            }
            int leftKey = left.firstKey();
            if (num >= leftKey) {
                right.put(num, right.getOrDefault(num, 0) + 1);
            } else {
                left.put(num, left.getOrDefault(num, 0) + 1);
                left.put(left.firstKey(), left.firstEntry().getValue() - 1);
                right.put(left.firstKey(), right.getOrDefault(left.firstKey(), 0) + 1);
                if (left.firstEntry().getValue() == 0) {
                    left.pollFirstEntry();
                }
            }
        }
    }

    public double findMedian() {
        if (leftCount != rightCount) {
            return left.firstKey();
        }
        return ((double) left.firstKey() + (double) right.firstKey()) / 2;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */