package leetcode.editor.interview._0409_BSTSequences;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> BSTSequences(TreeNode root) {
        // 归并思想,两边顺序可以替换
        List<List<Integer>> res = solve(root);
        return res;
    }

    private List<List<Integer>> solve(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            res.add(new ArrayList<>());
            return res;
        }
        List<List<Integer>>[] arr = new List[2];
        arr[0] = solve(root.left);
        arr[1] = solve(root.right);
        List<Integer> temp;
        for (int i = 0; i < arr[0].size(); i++) {
            for (int j = 0; j < arr[1].size(); j++) {
                if (arr[0].get(i).isEmpty() || arr[1].get(j).isEmpty()) {
                    temp = new ArrayList<>();
                    temp.add(root.val);
                    temp.addAll(arr[0].get(i));
                    temp.addAll(arr[1].get(j));
                    res.add(temp);
                    continue;
                }
                temp = new ArrayList<>();
                temp.add(root.val);
                _solve(0, 0, arr[0].get(i), arr[1].get(j), temp, res);
            }
        }
        return res;
    }

    private void _solve(int indexA, int indexB, List<Integer> a, List<Integer> b, List<Integer> curr, List<List<Integer>> res) {
        if (indexA == a.size() || indexB == b.size()) {
            List<Integer> temp = new ArrayList<>(curr);
            while (indexA < a.size()) {
                temp.add(a.get(indexA));
                indexA++;
            }
            while (indexB < b.size()) {
                temp.add(b.get(indexB));
                indexB++;
            }
            res.add(temp);
            return;
        }
        curr.add(a.get(indexA));
        _solve(indexA + 1, indexB, a, b, curr, res);
        curr.set(curr.size() - 1, b.get(indexB));
        _solve(indexA, indexB + 1, a, b, curr, res);
        curr.remove(curr.size() - 1);
    }
}