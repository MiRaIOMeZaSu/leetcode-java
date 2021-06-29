package leetcode.editor.contest._0305_SortedStack;

import java.util.Deque;
import java.util.LinkedList;

class SortedStack {
    Deque<Integer> stack = new LinkedList<>();
    Deque<Integer> temp = new LinkedList<>();

    public SortedStack() {
    }

    public void push(int val) {
        // 每一次都是插入排序
        while (!stack.isEmpty() && stack.peek() < val) {
            temp.push(stack.poll());
        }
        stack.push(val);
        while (!temp.isEmpty()){
            stack.push(temp.poll());
        }
    }

    public void pop() {
        stack.poll();
    }

    public int peek() {
        if (stack.isEmpty()) {
            return -1;
        }
        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}

/**
 * Your SortedStack object will be instantiated and called as such:
 * SortedStack obj = new SortedStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.isEmpty();
 */