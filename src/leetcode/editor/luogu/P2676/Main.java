package leetcode.editor.luogu.P2676;


import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        Scanner cin = new Scanner(System.in);
        int size = cin.nextInt(), target = cin.nextInt();
        int sum = 0;
        int count = 0;
        for (int i = 0; i < size; i++) {
            int input = cin.nextInt();
            if (sum > target) {
                if (input > queue.peek()) {
                    sum += input;
                    queue.add(input);
                    count++;
                    while (sum - queue.peek() > target) {
                        sum -= queue.peek();
                        queue.poll();
                        count--;
                    }
                }
                continue;
            }
            queue.add(input);
            sum += input;
            count++;
        }
        System.out.println(count);
    }
}
