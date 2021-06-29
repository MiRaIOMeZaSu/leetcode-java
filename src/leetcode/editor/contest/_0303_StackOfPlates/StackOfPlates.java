package leetcode.editor.contest._0303_StackOfPlates;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class StackOfPlates {
    List<Deque<Integer>> stacks = new ArrayList<>();
    int cap = 0;

    public StackOfPlates(int cap) {
        this.cap = cap;
    }

    public void push(int val) {
        if (cap == 0) {
            return;
        }
        int size = stacks.size();
        if (stacks.isEmpty() || stacks.get(size - 1).size() == cap) {
            stacks.add(new LinkedList<>());
            size++;
        }
        stacks.get(size - 1).push(val);
    }

    public int pop() {
        int size = stacks.size();
        if (size == 0) {
            return -1;
        }
        int res = stacks.get(size - 1).poll();
        if (stacks.get(size - 1).isEmpty()) {
            stacks.remove(size - 1);
        }
        return res;
    }

    public int popAt(int index) {
        int size = stacks.size();
        if (size <= index || index < 0) {
            return -1;
        }
        int res = stacks.get(index).poll();
        if (stacks.get(index).isEmpty()) {
            stacks.remove(index);
        }
        return res;
    }
}

/**
 * Your StackOfPlates object will be instantiated and called as such:
 * StackOfPlates obj = new StackOfPlates(cap);
 * obj.push(val);
 * int param_2 = obj.pop();
 * int param_3 = obj.popAt(index);
 */