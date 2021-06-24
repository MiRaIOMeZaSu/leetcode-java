package leetcode.editor._295_MedianFinder;


public class Main {
    public static void main(String[] args) {
        MedianFinder obj = new MedianFinder();
        obj.addNum(40);
        obj.addNum(12);
        obj.addNum(16);
        obj.addNum(14);
        obj.addNum(35);
        obj.addNum(19);
        double param_2 = obj.findMedian();
        System.out.println(param_2);
    }
}
