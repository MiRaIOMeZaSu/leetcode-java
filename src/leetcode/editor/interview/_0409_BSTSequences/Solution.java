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
                    if (!arr[0].get(i).isEmpty() || !arr[1].get(j).isEmpty()) {
                        temp = new ArrayList<>();
                        temp.addAll(arr[0].get(i));
                        temp.addAll(arr[1].get(j));
                        temp.add(root.val);
                        res.add(temp);
                    }
                    continue;
                }
                temp = new ArrayList<>();
                temp.add(root.val);
                temp.addAll(arr[0].get(i));
                temp.addAll(arr[1].get(j));
                res.add(temp);
                temp = new ArrayList<>();
                temp.add(root.val);
                temp.addAll(arr[1].get(j));
                temp.addAll(arr[0].get(i));
                res.add(temp);
            }
        }
        return res;
    }
}