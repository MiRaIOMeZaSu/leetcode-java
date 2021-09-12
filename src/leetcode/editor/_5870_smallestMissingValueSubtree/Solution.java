package leetcode.editor._5870_smallestMissingValueSubtree;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Node {
    int[] children = new int[2];
    int count = 0;
    int index;

    Node(int i) {
        index = i;
    }

    public void addChild(int node) {
        children[count] = node;
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
        int[] sortedNums = new int[size];
//        System.arraycopy(nums, 0, sortedNums, 0, size);
//        Arrays.sort(sortedNums);
//        int[] indexMap = new int[sortedNums[size - 1]];
//        for (int i = 0; i < size; i++) {
//            indexMap[sortedNums[i]] = i;
//        }
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
        if (node.count > 0) {
            set = solve(node.children[0]);
            max = Math.max(max, ans[node.children[0]]);
        }
        if (node.count > 1) {
            set.addAll(solve(node.children[1]));
            max = Math.max(max, ans[node.children[1]]);
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