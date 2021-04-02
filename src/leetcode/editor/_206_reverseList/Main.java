package leetcode.editor._206_reverseList;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode _1 = new ListNode(1);
        ListNode _2 = new ListNode(2);
        _1.next = _2;
        ListNode _3 = new ListNode(3);
        _2.next= _3;
        ListNode _4 = new ListNode(4);
        _3.next = _4;
        ListNode _5 = new ListNode(5);
        _4.next = _5;
        ListNode ret =  solution.reverseList(_1);
    }
}
