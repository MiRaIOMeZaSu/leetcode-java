package leetcode.editor._95_generateTrees;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public List<TreeNode> generateTrees(int n) {
        Map<String, List<TreeNode>> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            String key = String.valueOf(i) + "," + String.valueOf(i);
            List<TreeNode> temp = new ArrayList<>();
            temp.add(new TreeNode(i));
            map.put(key, temp);
        }
        // 动态规划
        for (int i = 2; i <= n; i++) {
            for (int start = 1; start + i - 1 <= n; start++) {
                String key = start + "," + (start + i - 1);
                if (!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }
                for (int root_index = start; root_index <= start + i - 1; root_index++) {
                    String leftKey = start + "," + (root_index - 1);
                    String rightKey = (root_index + 1) + "," + (start + i - 1);
                    List<TreeNode> empty = new ArrayList<>();
                    empty.add(null);
                    List<TreeNode> leftNodes = map.getOrDefault(leftKey, empty);
                    List<TreeNode> rightNodes = map.getOrDefault(rightKey, empty);
                    for (int x = 0; x < leftNodes.size(); x++) {
                        for (int y = 0; y < rightNodes.size(); y++) {
                            TreeNode root = new TreeNode(root_index);
                            TreeNode leftNode = copyNode(leftNodes.get(x));
                            TreeNode rightNode = copyNode(rightNodes.get(y));
                            root.left = leftNode;
                            root.right = rightNode;
                            map.get(key).add(root);
                        }
                    }
                }
            }
        }
        return map.get("1" + "," + String.valueOf(n));
    }

    public TreeNode copyNode(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode node = new TreeNode(root.val);
        if (root.left != null) {
            node.left = copyNode(root.left);
        }
        if (root.right != null) {
            node.right = copyNode(root.right);
        }
        return node;
    }

    public void insertToRight(TreeNode toInsert, TreeNode root) {
        TreeNode curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }
        curr.right = toInsert;
    }
}