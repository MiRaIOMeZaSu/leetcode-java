package leetcode.editor.LCCUP._20220416_._3_getNumber;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    // 哈希链表
    List<Integer> nodeList = new ArrayList<>();

    public int getNumber(TreeNode root, int[][] ops) {
        // 遍历root
        solve(root);
        nodeList.sort(Integer::compareTo);
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nodeList.size(); i++) {
            hashMap.put(nodeList.get(i), i);
        }
        boolean[] isRed = new boolean[nodeList.size()];
        for (int i = 0; i < ops.length; i++) {
            int left = hashMap.get(ops[i][1]);
            int curr = left;
            while (curr < nodeList.size() && nodeList.get(curr) <= ops[i][2]) {
                isRed[curr] = (ops[i][0] == 1);
                curr++;
            }
        }
        int ans = 0;
        for (int i = 0; i < isRed.length; i++) {
            if (isRed[i]) {
                ans++;
            }
        }
        return ans;
    }

    void solve(TreeNode root) {
        if (root == null) {
            return;
        }
        nodeList.add(root.val);
        solve(root.left);
        solve(root.right);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}