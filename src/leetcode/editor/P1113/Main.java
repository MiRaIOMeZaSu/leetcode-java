package leetcode.editor.P1113;

import java.util.*;

class Node implements Cloneable {
    int id;
    PriorityQueue<Integer> out;
    PriorityQueue<Integer> in;

    Node(int id) {
        this.id = id;
        out = new PriorityQueue<>();
        in = new PriorityQueue<>();
    }

    @Override
    public Object clone() {
        Node other = new Node(this.id);
        for (int i : out) {
            other.addOut(i);
        }
        for (int i : in) {
            other.addIn(i);
        }
        return other;
    }

    public int getCountIn() {
        return in.size();
    }

    public int getCountOut() {
        return out.size();
    }

    void addOut(int id) {
        out.add(id);
    }

    void removeOut(int id) {
        out.remove(id);
    }

    void addIn(int id) {
        in.add(id);
    }

    void removeIn(int id) {
        in.remove(id);
    }
}

public class Main {
    public static int getVE(int ve[], PriorityQueue<Integer> in, int cast[]) {
        int ret = -1;
        int temp;
        for (int i : in) {
            temp = ve[i] + cast[i];
            ret = temp > ret ? temp : ret;
        }
        return ret;
    }

    public static void main(String[] args) {
        // 使用逆拓扑排序
        // 回溯法
        // 所有可能性?
        // 对于多个出度为零的点的做法
        // 输入的都是出度
        // 先拓扑排序再逆拓扑排序
        Scanner cin = new Scanner(System.in);
        int size = cin.nextInt();
        // 每个以相应结点为弧的头的边的长度
        int cast[] = new int[size + 1 + 1];
        // cast[0]和cast[size+1]将初始化为0
        Node graph[] = new Node[size + 1 + 1];
        graph[0] = new Node(0);
        // 虚拟一个最开始的头指向所有入度为零的结点
        for (int i = 0; i < size; i++) {
            int id = cin.nextInt();
            graph[id] = new Node(id);
            cast[id] = cin.nextInt();
            int next = cin.nextInt();
            while (next != 0) {
                graph[next].addOut(id);
                graph[id].addIn(next);
                next = cin.nextInt();
            }
            if (graph[id].getCountIn() == 0) {
                graph[0].addOut(id);
                graph[id].addIn(0);
            }
        }
        graph[size + 1] = new Node(size + 1);
        for (int i = 1; i < size + 1; i++) {
            if (graph[i].getCountOut() == 0) {
                graph[i].addOut(size + 1);
                graph[size + 1].addIn(i);
            }
        }
        Node backup[] = new Node[size + 1 + 1];
        for (int i = 0; i <= size + 1; i++) {
            backup[i] = (Node) graph[i].clone();
        }
        // 此时出入度初始化完毕
        // 开始进行拓扑排序,每次选择入度为零的结点
        List<Integer> queue = new ArrayList<>();
        Queue<Integer> zeroIn = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        zeroIn.add(0);
        while (!zeroIn.isEmpty()) {
            int pivot = zeroIn.poll();
            queue.add(pivot);
            for (int i = pivot + 1; i <= size + 1; i++) {
                graph[i].removeIn(pivot);
                if (graph[i].getCountIn() == 0 && !visited.contains(i)) {
                    zeroIn.add(i);
                    visited.add(i);
                }
            }
        }
//        for (int i = 0; i <= size + 1; i++) {
//            graph[i] = (Node) backup[i].clone();
//        }
        // 开始逆拓扑排序
        /*
        Queue<Integer> zeroOut = new LinkedList<>();
        List<Integer> queueOut = new ArrayList<>();
        visited.clear();
        zeroOut.add(size + 1);
        while (!zeroOut.isEmpty()) {
            int pivot = zeroOut.poll();
            queueOut.add(pivot);
            for (int i = pivot - 1; i >= 0; i--) {
                graph[i].removeOut(pivot);
                if (graph[i].getCountOut() == 0 && !visited.contains(i)) {
                    zeroOut.add(i);
                    visited.add(i);
                }
            }
        }*/
//        List<Integer> reverseQueue = new ArrayList<>();
//        for (int i = queue.size() - 1; i >= 0; i--) {
//            reverseQueue.add(queue.get(i));
//        }
        // 初始化表格


        // 最后要输出的是关键路径的长度
        int ve[] = new int[size + 1 + 1];
        ve[0] = 0;
        for (int i = 1; i <= size + 1; i++) {
            ve[i] = getVE(ve, backup[i].in, cast);
        }
        System.out.println(ve[size + 1]);

//        int vl[] = new int[size + 1 + 1];
//        return;
    }
}
