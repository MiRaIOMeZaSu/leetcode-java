package leetcode.editor._855_ExamRoom;

import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        TreeSet<int[]> lines = new TreeSet<>(new CompareLine(10));
        lines.add(new int[]{1,2});
        System.out.println(lines.contains(new int[]{1,3}));
        System.out.println(lines.contains(new int[]{1,2}));
    }
}
