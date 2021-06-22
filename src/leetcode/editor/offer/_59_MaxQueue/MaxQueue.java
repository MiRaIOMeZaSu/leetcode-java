package leetcode.editor.offer._59_MaxQueue;

import java.util.Deque;
import java.util.LinkedList;

class MaxQueue {
    Deque<Integer> stack = new LinkedList<>();
    Deque<Integer> queue = new LinkedList<>();

    public MaxQueue() {
        // 使用单调栈
    }

    public int max_value() {
        if (stack.isEmpty()) {
            return -1;
        }
        return stack.peek();
    }

    public void push_back(int value) {
        queue.add(value);
        if (stack.isEmpty()) {
            stack.add(value);
            return;
        }
        while (!stack.isEmpty() && stack.peekLast() < value) {
            stack.pollLast();
        }
        stack.add(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        }
        int toRemove = queue.poll();
        if (!stack.isEmpty() && toRemove == stack.peek()) {
            stack.poll();
        }
        return toRemove;
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */