package leetcode.editor._855_ExamRoom;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

// 使用二叉树存储

class CompareLine implements Comparator<int[]> {
    private int N;

    public CompareLine(int N) {
        this.N = N;
    }

    @Override
    public int compare(int[] o1, int[] o2) {
        // 比较线段的中点前后和中点到两边的最短位置
        if (o1[0] == o2[0] && o1[1] == o2[1]) {
            return 0;
        }
        // 计算mid与距离
        int mid1 = (o1[0] + o1[1]) / 2;
        int mid2 = (o2[0] + o2[1]) / 2;
        int dis1 = this.distance(o1, mid1);
        int dis2 = this.distance(o2, mid2);
        if (dis1 == dis2) {
            return mid1 > mid2 ? -1 : 1;
        }
        return dis1 > dis2 ? 1 : -1;
    }

    public int distance(int[] line, int mid) {
        if (line[0] == -1 || line[1] == this.N) {
            return line[1] - line[0] -1;
        }
        return Math.min(mid - line[0], line[1] - mid);
    }
}

class ExamRoom {
    private TreeSet<int[]> lines;
    private int N;

    public ExamRoom(int N) {
        this.N = N;
        this.lines = new TreeSet<>(new CompareLine(this.N));
        // 放入两个虚拟考生用于定位两端
        lines.add(new int[]{-1, N});
    }

    public int seat() {
        // 获取最符合条件的线段
        int[] first = lines.last();
        if (first[0] == -1) {
            int right = first[1];
            lines.remove(first);
            lines.add(new int[]{0, right});
            return 0;
        } else if (first[1] == this.N) {
            int left = first[0];
            lines.remove(first);
            lines.add(new int[]{left, N - 1});
            return N - 1;
        }
        lines.remove(first);
        // 应该靠左(向下取整即可)
        int mid = (first[0] + first[1]) / 2;
        lines.add(new int[]{first[0], mid});
        lines.add(new int[]{mid, first[1]});
        return mid;
    }

    public void leave(int p) {
        // 需要寻找两端的线段并连接
        List<int[]> arrayList = new ArrayList<>();
        for (int[] i : this.lines) {
            if (i[0] == p || i[1] == p) {
                arrayList.add(i);
            }
        }
        for (int[] i : arrayList) {
            this.lines.removeIf(
                    line -> line[0] == i[0] && line[1] == i[1]
            );
        }
        if (arrayList.isEmpty()) {
            return;
        }
        // 特殊情况
        if (p == 0) {
            this.lines.add(new int[]{-1, arrayList.get(0)[1]});
            return;
        } else if (p == this.N - 1) {
            this.lines.add(new int[]{arrayList.get(0)[0], N});
            return;
        }
        if (arrayList.get(0)[1] == p) {
            this.lines.add(new int[]{arrayList.get(0)[0], arrayList.get(1)[1]});
        } else {
            this.lines.add(new int[]{arrayList.get(1)[0], arrayList.get(0)[1]});
        }
    }
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(N);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */