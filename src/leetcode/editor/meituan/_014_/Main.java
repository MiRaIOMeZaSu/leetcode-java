package leetcode.editor.meituan._014_;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int x, y;
        char A = 'A';
        char B = 'B';
        Scanner scanner = new Scanner(System.in);
        x = scanner.nextInt();
        y = scanner.nextInt();
        scanner.nextLine();
        String temp = scanner.nextLine();
        String[] strengthString = temp.trim().split(" ");
        int[][] strength = new int[strengthString.length][2];
        int[][] yaStrength = new int[strengthString.length][];
        for (int i = 0; i < strength.length; i++) {
            strength[i][0] = Integer.parseInt(strengthString[i]);
            yaStrength[i] = strength[i];
        }
        // 字典序最小: 序号越小越应该选择A
        // 会产生多种情况相同的前提条件: x==y
        StringBuilder stringBuilder = new StringBuilder();
        if (x == y) {
            // 此时任意分配均无所谓!
            for (int i = 0; i < x; i++) {
                stringBuilder.append(A);
            }
            for (int i = 0; i < x; i++) {
                stringBuilder.append(B);
            }
        } else {
            // 实力值向一遍偏移
            // 所有人的实力值不相同,因此不必考虑会产生多种情况
            Arrays.sort(yaStrength, ((o1, o2) -> o2[0] - o1[0]));
            // 决定那些赋值为1即可
            if (x < y) {
                // x队数量更小,赋值为A,队员更强
                for (int i = 0; i < x; i++) {
                    yaStrength[i][1] = 1;
                }
            } else {
                // y队数量更小,赋值为B,队员更强
                for (int i = y; i < x + y; i++) {
                    yaStrength[i][1] = 1;
                }
            }
            for (int i = 0; i < x + y; i++) {
                if (strength[i][1] == 1) {
                    stringBuilder.append(A);
                } else {
                    stringBuilder.append(B);
                }
            }
        }
        System.out.print(stringBuilder.toString());
    }
}
