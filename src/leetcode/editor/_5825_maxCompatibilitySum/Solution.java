package leetcode.editor._5825_maxCompatibilitySum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Solution {
    class TreeNode {
        TreeNode root;
        int isLeft;
        boolean enable = true;
        int index = 0;
        TreeNode[] nodes = new TreeNode[]{null, null};
        int count = 0;
        List<int[]> list = new ArrayList<>();
    }

    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        // 使用二叉树
        int m = students.length;
        int n = students[0].length;
        TreeNode treeNode = new TreeNode();
        int nodeCount = 0;
        List<TreeNode> nodes = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            TreeNode curr = treeNode;
            for (int j = 0; j < n; j++) {
                int a = students[i][j];
                if (curr.nodes[a] == null) {
                    curr.nodes[a] = new TreeNode();
                    curr.nodes[a].root = curr;
                    curr.nodes[a].isLeft = a;
                }
                curr = curr.nodes[a];
            }
            if (curr.index == 0) {
                curr.index = nodeCount + 1;
                nodeCount++;
                nodes.add(curr);
            }
            curr.count++;
        }
        int result = 0;
        boolean[] visit = new boolean[m];
        int count = 0;
        while (count < m) {
            for (int i = 0; i < m; i++) {
                if (visit[i]) {
                    continue;
                }
                TreeNode curr = treeNode;
                int point = 0;
                int j = 0;
                for (; j < n; j++) {
                    int a = mentors[i][j];
                    if (curr.nodes[a] != null) {
                        curr = curr.nodes[a];
                        point++;
                        check(curr);
                    } else if (curr.nodes[(a + 1) % 2] != null) {
                        curr = curr.nodes[(a + 1) % 2];
                        check(curr);
                    } else {
                        curr.root.nodes[curr.isLeft] = null;
                        check(curr.root);
                        break;
                    }
                }
                if (j == n) {
                    curr.list.add(new int[]{i, point});
                }
            }
            for (int i = 0; i < nodeCount; i++) {
                TreeNode node = nodes.get(i);
                if (node.count == 0) {
                    continue;
                }
                if (node.count > node.list.size()) {
                    node.count -= node.list.size();
                    for (int j = 0; j < node.list.size(); j++) {
                        visit[node.list.get(j)[0]] = true;
                        result += node.list.get(j)[1];
                    }
                    count += node.list.size();
                    node.list = new ArrayList<>();
                } else {
                    node.list.sort((o1, o2) -> o2[1] - o1[1]);
                    for (int j = 0; j < node.count; j++) {
                        visit[node.list.get(j)[0]] = true;
                        result += node.list.get(j)[1];
                    }
                    node.list = new ArrayList<>();
                    count += node.count;
                    node.count = 0;
                    node.root.nodes[node.isLeft] = null;
                    check(node.root);
                }
            }
        }
        return result;
    }

    private void check(TreeNode node) {
        for (int i = 0; i < 2; i++) {
            if (node.nodes[i] != null) {
                TreeNode[] nodes = node.nodes[i].nodes;
                if (nodes[0] == null && nodes[1] == null && node.nodes[i].count == 0) {
                    node.nodes[i] = null;
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.maxCompatibilitySum(new int[][]{{0,0,1,0,1},{1,0,1,1,1}},
                new int[][]{{1,0,1,0,1},{1,0,1,1,0}});
        System.out.println(result);
    }
}