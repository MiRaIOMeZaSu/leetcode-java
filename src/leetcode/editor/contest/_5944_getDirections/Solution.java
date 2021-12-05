package leetcode.editor.contest._5944_getDirections;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    int startValue;
    int destValue;
    Deque<Character> start = new LinkedList<>();
    Deque<Character> dest = new LinkedList<>();

    public String getDirections(TreeNode root, int startValue, int destValue) {
        // 使用搜索
        this.startValue = startValue;
        this.destValue = destValue;
        start = new LinkedList<>();
        search(root, start, startValue);
        search(root, dest, destValue);
        int times = Math.min(start.size(), dest.size());
        for (int i = 0; i < times; i++) {
            if (start.peek().equals(dest.peek())) {
                start.poll();
                dest.poll();
            }else{
                break;
            }
        }
        // start的需要反转,dest的不需要
        Map<Character, Character> map = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < start.size(); i++) {
            stringBuilder.append('U');
        }
        while (!dest.isEmpty()) {
            stringBuilder.append(dest.poll());
        }
        return stringBuilder.toString();
    }

    private boolean search(TreeNode root, Deque<Character> characters, int target) {
        if (root.val == target) {
            return true;
        }
        // 都不等于则继续
        if (root.left != null) {
            characters.add('L');
            if (search(root.left, characters, target)) {
                return true;
            }
            characters.pollLast();
        }
        if (root.right != null) {
            characters.add('R');
            if (search(root.right, characters, target)) {
                return true;
            }
            characters.pollLast();
        }
        return false;
    }
}