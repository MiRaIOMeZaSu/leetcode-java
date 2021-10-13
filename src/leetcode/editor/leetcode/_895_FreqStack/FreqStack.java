package leetcode.editor._895_FreqStack;

import java.util.*;

class IdItem {
    Stack<Integer> ids;
    int val;

    public IdItem(int id, int val) {
        this.val = val;
        ids = new Stack<>();
        ids.push(id);
    }
}

class FreqStack {
    // 延迟删除
    // 栈+集合+TreeSet

    int globalId = 0;
    Queue<IdItem> frequency;
    Map<Integer, IdItem> table;

    public FreqStack() {
        frequency = new PriorityQueue<>((o1, o2) -> {
            if (o1.ids.size() != o2.ids.size()) {
                return o2.ids.size() - o1.ids.size();
            } else {
                return o2.ids.peek() - o1.ids.peek();
            }
        });
        table = new HashMap<>();
    }

    public void push(int val) {
        IdItem temp;
        if (table.containsKey(val)) {
            temp = table.get(val);
            frequency.remove(temp);
            temp.ids.push(globalId);
        } else {
            temp = new IdItem(globalId, val);
            table.put(val, temp);
        }
        frequency.add(temp);
        globalId++;
    }

    public int pop() {
        IdItem temp = frequency.poll();
        temp.ids.pop();
        if (temp.ids.isEmpty()) {
            table.remove(temp.val);
        } else {
            frequency.add(temp);
        }
        return temp.val;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */