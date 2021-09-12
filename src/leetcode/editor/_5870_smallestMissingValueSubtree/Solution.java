package leetcode.editor._5870_smallestMissingValueSubtree;

import java.lang.reflect.Array;
import java.util.*;

class Node {
    List<Integer> children = new ArrayList<>();
    int count = 0;
    int index;

    Node(int i) {
        index = i;
    }

    public void addChild(int node) {
        children.add(node);
        count++;
    }
}

class Solution {
    int[] ans;
    Node[] nodes;
    int[] nums;

    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        // 重点在于子树的寻找(并集)
        int size = parents.length;
        nodes = new Node[size];
        this.nums = nums;
        for (int i = 0; i < size; i++) {
            nodes[i] = new Node(i);
        }
        int root = 0;
        for (int i = 0; i < size; i++) {
            if (parents[i] == -1) {
                root = i;
                continue;
            }
            nodes[parents[i]].addChild(i);
        }
        // 树建立完毕
        ans = new int[size];
        solve(root);
        return ans;
    }

    private Set<Integer> solve(int i) {
        Node node = nodes[i];
        int max = 1;
        Set<Integer> set = new HashSet<>();
        for (int j = 0; j < node.children.size(); j++) {
            int child = node.children.get(j);
            set.addAll(solve(child));
            max = Math.max(max, ans[child]);
        }
        set.add(nums[i]);
        while (true) {
            if (set.contains(max)) {
                max++;
            } else {
                break;
            }
        }
        ans[i] = max;
        return set;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.smallestMissingValueSubtree(
                new int[]{-1, 2, 3, 0, 2, 4, 1},
                new int[]{2, 3, 4, 5, 6, 7, 8});
    }
}