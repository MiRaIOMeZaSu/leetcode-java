package leetcode.editor.offer._59_MaxQueue;

public class Main {
    public static void main(String[] args) {
        MaxQueue obj = new MaxQueue();
        obj.push_back(1);
        obj.push_back(2);
        int param_1 = obj.max_value();
        System.out.println(param_1);
        int param_2 = obj.pop_front();
        System.out.println(param_2);
        int param_3 = obj.max_value();
        System.out.println(param_3);
    }
}
