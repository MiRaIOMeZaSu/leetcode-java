package leetcode.editor._P1152;

import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        Scanner cin = new Scanner(System.in);
        int size = cin.nextInt();
        int pre = cin.nextInt();
        int next;
        for (int i = 1; i < size; i++) {
            next = cin.nextInt();
            set.add(Math.abs(pre - next));
            pre = next;
        }
        if (set.first() == 1 && set.size() == size - 1 && set.last() == size - 1) {
            System.out.println("Jolly");
        } else {
            System.out.println("Not jolly");
        }
    }
}
