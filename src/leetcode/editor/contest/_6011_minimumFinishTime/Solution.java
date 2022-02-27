package leetcode.editor.contest._6011_minimumFinishTime;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

class Tire {
    public Tire(int nextCost, int f, int r, int times, int index) {
        this.nextCost = nextCost;
        this.f = f;
        this.r = r;
        this.times = times;
        this.index = index;
    }

    int nextCost;
    int f;
    int r;
    int times;
    int index;
}

class Solution {
    public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
        int ans = 0;
        PriorityQueue<Tire> priorityQueue = new PriorityQueue<>(new Comparator<Tire>() {
            @Override
            public int compare(Tire o1, Tire o2) {
                return o1.nextCost - o2.nextCost;
            }
        });
        int size = tires.length;
        for (int i = 0; i < size; i++) {
            priorityQueue.add(new Tire(tires[i][0], tires[i][0], tires[i][1], 0, i));
        }
        int lastIndex = -1;
        int lastTime = -1;
        for (int i = 0; i < numLaps; i++) {
            Tire head = priorityQueue.poll();
            // 第二次相同的?
            if (head.times > 0 && (head.index != lastIndex || head.times != lastTime)) {
                continue;
            }
            System.out.println(head.index);
            ans += head.nextCost + changeTime;
            if (head.times == 0) {
                priorityQueue.add(new Tire(head.f, head.f, head.r, 0, head.index));
            }
            head.times += 1;
            head.nextCost = head.f * (int) Math.pow(head.r, head.times - 1) - changeTime;
            priorityQueue.add(head);
            lastIndex = head.index;
            lastTime = head.times;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.minimumFinishTime(new int[][]{{2, 3}, {3, 4}}, 5, 4);
    }
}