package leetcode.editor._1482_minDays;


import java.util.*;

class ListNode {
    int index;
    ListNode next = null;
    ListNode pre = null;

    ListNode(int index) {
        this.index = index;
    }
}

class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        // 必须相邻
        int bloomCount = k * m;
        TreeMap<Integer, ArrayList<Integer>> dayset = new TreeMap<>((o1, o2) -> o2 - o1);
        int size = bloomDay.length;
        for (int i = 0; i < size; i++) {
            if (!dayset.containsKey(bloomDay[i])) {
                dayset.put(bloomDay[i], new ArrayList<>());
            }
            dayset.get(bloomDay[i]).add(i);
        }
        if (bloomCount >= bloomDay.length) {
            if (bloomCount == bloomDay.length) {
                return dayset.firstKey();
            } else {
                return -1;
            }
        }
        // 使用链表
        // 初始化
        ListNode head = new ListNode(0);
        ListNode tail = new ListNode(size - 1);
        head.next = tail;
        tail.pre = head;
        while (true) {
            ListNode curr = head;
            ListNode next = head.next;
            int currBloom = 0;
            ArrayList<Integer> indexArr = dayset.firstEntry().getValue();
            int i = 0;
            while (i < indexArr.size()) {
                int index = indexArr.get(i);
                if (index > curr.index && index < next.index) {
                    curr.next = new ListNode(index - 1);
                    curr.next.pre = curr;
                    next.pre = new ListNode(index + 1);
                    next.pre.next = next;
                    next.pre.pre = curr.next;
                    curr.next.next = next.pre;
                    next = curr.next;
                } else if (index == curr.index || index == next.index) {
                    if (index == curr.index) {
                        curr.index += 1;
                    } else {
                        next.index -= 1;
                    }
                    if (curr.index > next.index) {
                        // 移除这两个节点
                        if(curr.pre != null){
                            curr.pre.next = next.next;
                        }else{
                            head = next.next;
                        }
                        if (next.next != null) {
                            next.next.pre = curr.pre;
                        }
                        curr = next.next;
                    }
                } else {
                    // 此时必然在之后的线段中
                    currBloom += (next.index - curr.index + 1) / k;
                    curr = next.next;
                    if (curr == null) {
                        return dayset.firstKey();
                    }
                    next = curr.next;
                    continue;
                }
                i++;
            }
            while (curr != null && currBloom < m) {
                currBloom += (next.index - curr.index + 1) / k;
                curr = next.next;
                if (curr != null) {
                    next = curr.next;
                } else if (currBloom < m) {
                    // curr == null
                    return dayset.firstKey();
                } else {
                    break;
                }
            }
            if (currBloom < m) {
                // 结果
                return dayset.firstKey();
            } else {
                dayset.remove(dayset.firstKey());
            }
        }
    }
}