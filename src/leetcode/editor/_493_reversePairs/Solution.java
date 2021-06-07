package leetcode.editor._493_reversePairs;

import java.util.*;

class ListNode {
    int val;
    int count;
    boolean isPivot = false;
    ListNode next = null;
    ListNode pre = null;

    ListNode(int x, boolean y) {
        this.val = x;
        this.count = 0;
        this.isPivot = y;
    }
}

class Solution {
    public int reversePairs(int[] nums) {
        // 往后存且一边存一遍计算即可,此时必然满足i < j
        // 数字的大小不限!
        // 数组长度最长为50000
        // 实际是可以实现线段树的(需要十万空间
        int ret = 0;
        HashMap<Integer, ListNode> map = new HashMap<>(60);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!map.containsKey(num)) {
                map.put(num, new ListNode(num, false));
                list.add(num);
            }
            map.get(num).count++;
            int tempNum = num >> 1;
            if (!map.containsKey(tempNum)) {
                list.add(tempNum);
                map.put(tempNum, new ListNode(tempNum, true));
            } else {
                map.get(tempNum).isPivot = true;
            }
        }
        // 要寻找的是一个越小越好的数
        list.sort(Comparator.naturalOrder());
        for (int i = 0; i < list.size(); i++) {
            // 倒序构造列表?
            // 使用双向便于链表更新?
            ListNode temp = map.get(list.get(i));
            if (i - 1 >= 0) {
                temp.next = map.get(list.get(i - 1));
            }
            if (i + 1 < list.size()) {
                temp.pre = map.get(list.get(i + 1));
            }
        }
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            ListNode temp = map.get(num);
            temp.count--;
            if (temp.count == 0) {
                if (!temp.isPivot || (temp.isPivot && !map.containsKey(num * 2) && !map.containsKey(num * 2 + 1))) {
                    if (temp.pre != null) {
                        temp.pre.next = temp.next;
                        if (temp.next != null) {
                            temp.next.pre = temp.pre;
                        }
                    } else {
                        if (temp.next != null) {
                            temp.next.pre = null;
                        }
                    }
                }
            }
            int target = num >> 1;
            if ((num | 1) == num) {
                // num为奇数,target可以包含
                ret += map.get(target).count;
            }
            ListNode curr = map.get(target).next;
            while (curr != null) {
                ret += curr.count;
                curr = curr.next;
            }
        }
        return ret;
    }
}