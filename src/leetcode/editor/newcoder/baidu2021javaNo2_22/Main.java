package leetcode.editor.newcoder.baidu2021javaNo2_22;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node {
    int index;
    int father;
    // div表示与当前father的距离
    int div = -1;
    int sonCount = 0;

    Node(int index) {
        this.index = index;
        father = index;
    }
}

public class Main {
    public static void main(String[] args) {
        // 链表或者说是图
        // 只需要看自己的父节点在何处
        // 待优化,存储
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        String command;
        int curr, target;
        Queue<Integer>[] queue = new LinkedList[n];
        // 初始化图
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i + 1);
        }
        // 难点在于查询间隔
        for (int i = 0; i < m; i++) {
            command = scanner.next();
            curr = scanner.nextInt() - 1;
            target = scanner.nextInt() - 1;
            if (command.equals("C")) {
                // 移动操作
                int prevSonCount = nodes[target].sonCount;
                nodes[target].sonCount += nodes[curr].sonCount + 1;
                nodes[curr].father = target + 1;
                nodes[curr].div = prevSonCount;
            } else {
                // 查询操作
                if(curr==target){
                    System.out.println(0);
                    continue;
                }
                int[] aFather = getTopFather(nodes[curr], nodes);
                int[] bFather = getTopFather(nodes[target], nodes);
                if (aFather[0] != bFather[0]) {
                    System.out.println(-1);
                } else {
                    int a = aFather[1];
                    int b = bFather[1];
                    if (a == -1) {
                        System.out.println(b);
                        continue;
                    }
                    if (b == -1) {
                        System.out.println(a);
                        continue;
                    }
                    System.out.println(Math.abs(a - b) - 1);
                }
            }
        }
    }

    public static int[] getTopFather(Node node, Node[] nodes) {
        int[] ret = new int[2];
        int temp = 0;
        while (node.father != node.index) {
            temp += node.div + 1;
            node = nodes[node.father - 1];
        }
        temp--;
        ret[0] = node.index;
        ret[1] = temp;
        return ret;
    }
}
