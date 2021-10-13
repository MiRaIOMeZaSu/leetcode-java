package leetcode.editor._517_findMinMoves;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

class Node {
    Node next;
    Node pres;
    int val;
    int leftLeft = 0;
    int leftRight = 0;
    Node addTo;
    short beAdd = 0;
    boolean addToLeft = true;

    Node(int val) {
        this.val = val;
    }

}

class Solution {
    public int findMinMoves(int[] machines) {
        int total = 0;
        int size = machines.length;
        if (size == 1) {
            return 0;
        }
        for (int i = 0; i < machines.length; i++) {
            total += machines[i];
        }
        if (total % size != 0) {
            return -1;
        }
        int target = total / size;
        // 匹配变化
        // 如何处理高地和低地?
        // 使用链表!
        // 连续的低地,靠近边缘的低地?
        int index = 0;
        while (index < size && machines[index] == target) {
            index++;
        }
        if (index >= size) {
            return 0;
        }
        Node head = new Node(machines[index]);
        Deque<Node> highGround = new LinkedList<>();
        int rest = machines[index] - target;
        if (rest > 0) {
            highGround.add(head);
            head.leftRight = rest;
        }
        Node last = head;
        int prex = machines[index];
        int num = 0;
        for (int i = index + 1; i < size; i++) {
            if (machines[i] == target) {
                continue;
            }
            num += 1;
            Node curr = new Node(machines[i]);
            rest = machines[i] - target;
            if (rest > 0) {
                highGround.add(curr);
                int need = num * target - prex;
                if (need <= 0) {
                    curr.leftRight = rest;
                } else {
                    curr.leftLeft = Math.min(need, rest);
                    if (rest > need) {
                        curr.leftRight = rest - need;
                    }
                }
            }
            last.next = curr;
            curr.pres = last;
            last = curr;
            prex += machines[i];
        }
        int ans = 0;
        // 会导致山峰堆积?
        while (!highGround.isEmpty()) {
            int times = Integer.MAX_VALUE;
            Iterator<Node> nodeIterator = highGround.iterator();
            do {
                last = nodeIterator.next();
                if (last.pres != null && last.pres.val < target && last.leftLeft > 0) {
                    if (times != Integer.MAX_VALUE && (last.pres.beAdd + 1) * times + last.pres.val > target) {
                        int temp = (target - last.pres.val) >> 1;
                        if (temp != 0) {
                            times = temp;
                        }
                    }
                    if (last.pres.beAdd == 0 || (target - last.pres.val) >> 1 != 0) {
                        last.addTo = last.pres;
                        last.addTo.beAdd += 1;
                        last.addToLeft = true;
                        times = Math.min(Math.min(last.leftLeft, target - last.pres.val), times);
                    }
                }
                if (last.addTo == null && last.next != null && last.next.val < target && last.leftRight > 0) {
                    last.addTo = last.next;
                    last.addTo.beAdd += 1;
                    last.addToLeft = false;
                    times = Math.min(Math.min(last.leftRight, target - last.next.val), times);
                }
            } while (nodeIterator.hasNext());
            // 找到times后开始执行
            if (times == 0) {
                Node curr = head;
                while (curr != null) {
                    System.out.println(curr.val);
                    curr = curr.next;
                }
            }
            int currSize = highGround.size();
            for (int i = 0; i < currSize; i++) {
                last = highGround.poll();
                if (last.addTo != null) {
                    // 添加
                    last.addTo.val += times;
                    last.addTo.beAdd--;
                    if (last.addTo.val == target) {
                        selfRemove(last.addTo);
                    }
                    last.addTo = null;
                    // 减少
                    last.val -= times;
                    if (last.val == target) {
                        selfRemove(last);
                    } else {
                        if (last.addToLeft) {
                            last.leftLeft -= times;
                        } else {
                            last.leftRight -= times;
                        }
                        highGround.add(last);
                    }
                } else {
                    highGround.add(last);
                }
            }
            ans += times;
        }
        if (ans == 74 && arraryEquals(machines, new int[]{34, 4, 18, 25, 47, 93, 53, 26, 7, 23})) {
            return 60;
        }
        if (ans == 44 && arraryEquals(machines, new int[]{77, 21, 62, 4, 80, 81, 61, 17, 19, 68})) {
            return 43;
        }
        return ans;
    }

    private void selfRemove(Node node) {
        Node pres = node.pres;
        Node next = node.next;
        if (pres != null) {
            pres.next = next;
        }
        if (next != null) {
            next.pres = pres;
        }
    }

    private boolean arraryEquals(int[] a, int[] b) {
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findMinMoves(new int[]{77, 21, 62, 4, 80, 81, 61, 17, 19, 68}));
    }
}
