package leetcode.editor._855_ExamRoom;

public class Main {
    public static void main(String[] args) {
        ExamRoom obj = new ExamRoom(20);
        System.out.println(obj.seat());
        System.out.println(obj.seat());
        System.out.println(obj.seat());
        System.out.println(obj.seat());
        obj.leave(0);
        System.out.println(obj.seat());
    }
}
