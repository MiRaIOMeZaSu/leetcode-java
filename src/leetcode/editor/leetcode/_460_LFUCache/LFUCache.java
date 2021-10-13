package leetcode.editor._460_LFUCache;

// 使用链表+哈希表


import java.util.HashMap;
import java.util.PriorityQueue;

class ListNode {
    int key;
    ListNode next;
    ListNode pre;

    ListNode(int key) {
        this.key = key;
        next = null;
        pre = null;
    }

    ListNode(int key, ListNode next, ListNode pre) {
        this.key = key;
        this.next = next;
        this.pre = pre;
    }
}

class LFUCache {
    int capacity;
    // 键与值的映射
    HashMap<Integer, Integer> table;
    // 链表的头尾
    HashMap<Integer, ListNode[]> bucket;
    // 在链表中的位置
    HashMap<Integer, ListNode> reflect;
    // 使用频率
    HashMap<Integer, Integer> count;
    // 最小堆维护数量
    PriorityQueue<Integer> queue;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        table = new HashMap<>();
        reflect = new HashMap<>();
        count = new HashMap<>();
        bucket = new HashMap<>();
        queue = new PriorityQueue<>();
    }

    public int get(int key) {
        if(capacity==0){
            return -1;
        }
        if (table.containsKey(key)) {
            update(key);
            return table.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if(capacity==0){
            return;
        }
        table.put(key, value);
        while (table.size() > capacity) {
            // 移除操作
            // 要移除的项的使用次数
            int targetTimes = queue.peek();
            int target;
            ListNode[] arr = bucket.get(targetTimes);
            if (arr[0].key == arr[1].key) {
                target = arr[0].key;
                bucket.remove(targetTimes);
                queue.remove(targetTimes);
            } else {
                target = arr[0].key;
                bucket.put(targetTimes, new ListNode[]{arr[0].next, arr[1]});
            }
            table.remove(target);
            count.remove(target);
            reflect.remove(target);
        }
        update(key);
    }

    private void update(int key) {
        count.put(key, count.getOrDefault(key, 0) + 1);
        if (!reflect.containsKey(key)) {
            reflect.put(key, new ListNode(key));
        }
        ListNode node = reflect.get(key);
        int num = count.get(key);
        if (num != 1) {
            // 此时必然包含
            ListNode[] array = bucket.get(num - 1);
            if (array[0].key == array[1].key) {
                // 进行删除操作
                bucket.remove(num - 1);
                queue.remove(num - 1);
            } else {
                // 此时相同使用词数有多个
                if (array[0].key == node.key) {
                    bucket.put(num - 1, new ListNode[]{array[0].next, array[1]});
                } else if (array[1].key == node.key) {
                    bucket.put(num - 1, new ListNode[]{array[0], array[1].pre});
                } else {
                    ListNode pre = node.pre;
                    ListNode next = node.next;
                    next.pre = pre;
                    pre.next = next;
                }
            }
        }
        // 如果等于1,则说明之前是零,无需删除操作
        ListNode[] arr;
        if (bucket.containsKey(num)) {
            bucket.get(num)[1].next = node;
            node.pre = bucket.get(num)[1];
            arr = new ListNode[]{bucket.get(num)[0], node};
        } else {
            arr = new ListNode[]{node, node};
            queue.add(num);
        }
        bucket.put(num, arr);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */