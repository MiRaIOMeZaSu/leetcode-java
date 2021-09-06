package leetcode.editor.meituan._001_;

import java.util.*;

public class Main {
    private static int[] pre;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] staff = new int[size];
        int[] takeout = new int[size];
        pre = new int[size];
        staff[0] = scanner.nextInt();
        pre[0] = staff[0];
        for (int i = 1; i < size; i++) {
            staff[i] = scanner.nextInt();
            pre[i] = pre[i - 1] + staff[i];
        }
        for (int i = 0; i < size; i++) {
            takeout[i] = scanner.nextInt();
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            int a = pre[o1[1]] - pre[o1[0]];
            int b = pre[o2[1]] - pre[o2[0]];
            return b - a;
        });
        TreeSet<Integer> todel = new TreeSet<>();

        queue.add(new int[]{0, size - 1});
        for (int i = 0; i < size; i++) {
            todel.add(takeout[i]);
            int[] temp = queue.peek();
            while (true) {
                for (int a : todel) {
                    a--;
                    if (a <= temp[1] && a >= temp[0]) {
                        queue.poll();
                        int[] temp1 = new int[]{temp[0], a - 1};
                        int[] temp2 = new int[]{a + 1, temp[1]};
                        if (temp1[0] <= a - 1) {
                            queue.add(temp1);
                        }
                        if (a + 1 <= temp[1]) {
                            queue.add(temp2);
                        }
                        todel.remove(a + 1);
                        break;
                    }
                }
                if (queue.isEmpty()) {
                    System.out.println(0);
                    return;
                }
                if (temp[0] == queue.peek()[0] && temp[1] == queue.peek()[1]) {
                    int a = pre[temp[1]];
                    int b;
                    if (temp[0] == 0) {
                        b = 0;
                    } else {
                        b = pre[temp[0] - 1];
                    }
                    System.out.println(a - b);
                    break;
                }
                temp = queue.peek();
            }
        }
    }
}
