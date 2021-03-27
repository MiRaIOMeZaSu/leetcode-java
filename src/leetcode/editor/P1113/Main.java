package leetcode.editor.P1113;

import java.util.*;

class Node implements Cloneable {
    int id;
    int outCount;
    List<Integer> in;

    Node(int id) {
        this.id = id;
        in = new ArrayList<>();
    }

    @Override
    public Object clone() {
        Node other = new Node(this.id);
        for (int i : in) {
            other.addIn(i);
        }
        return other;
    }

    public int getCountIn() {
        return in.size();
    }



    void addIn(int id) {
        in.add(id);
    }

    void removeIn(int id) {
        for (int i = 0; i < in.size(); i++) {
            if(in.get(i) == id){
                in.remove(in.get(i));
            }
        }
    }
}

public class Main {
    public static int getVE(int ve[], ArrayList<Integer> in, int cast[]) {
        int ret = 0;
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
                graph[next].outCount++;
                graph[id].addIn(next);
                next = cin.nextInt();
            }
            if (graph[id].getCountIn() == 0) {
                graph[0].outCount++;
                graph[id].addIn(0);
            }
        }
        graph[size + 1] = new Node(size + 1);
        for (int i = 1; i < size + 1; i++) {
            if (graph[i].outCount == 0) {
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
        // 最后要输出的是关键路径的长度
        int ve[] = new int[size + 1 + 1];
        ve[0] = 0;
        for (int i = 1; i <= size + 1; i++) {
            ve[i] = getVE(ve, (ArrayList<Integer>) backup[i].in, cast);
        }
        System.out.println(ve[size + 1]);
    }
}
