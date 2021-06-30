package leetcode.editor.offer._37_Codec;


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
public class Codec {
    List<int[]> arr = new ArrayList<>();

    private int[] getPos(int pos) {
        while (arr.size() <= pos) {
            arr.add(null);
        }
        if (arr.get(pos) != null) {
            return arr.get(pos);
        }
        pos++;
        int i = 1;
        while (true) {
            if (Math.pow(2, i) - 1 >= pos) {
                break;
            }
            i++;
        }
        i--;
        int temp = (int) (Math.pow(2, i) - 1);
        int rest = pos - temp;
        int father = (int) ((temp + 1) / 2 - 1 + Math.ceil((double) rest / (double) 2));
        int left = (temp + 1) * 2 - 1 + rest * 2 - 1;
        int right = left + 1;
        //  var left = (temp + 1) * 2 - 1 +
        // 此时i为当前pos的层数
        int[] res = new int[]{father - 1, left - 1, right - 1};
        arr.set(pos, res);
        return res;
    }

    private void solve(TreeNode root, int pos_root, List<String> list) {
        while (list.size() < pos_root + 1) {
            list.add("null");
        }
        list.set(pos_root, String.valueOf(root.val));
        int[] pos = getPos(pos_root);
        if (root.left != null) {
            solve(root.left, pos[1], list);
        }
        if (root.right != null) {
            solve(root.right, pos[2], list);
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // 使用深度优先,每个父节点为子节点设置序号
        List<String> ret = new ArrayList<>();
        if (root != null) {
            solve(root, 0, ret);
        }
        String string = "";
        for (int i = 0; i < ret.size(); i++) {
            string += ret.get(i);
            if (i != ret.size() - 1) {
                string += ",";
            }
        }
        return string;
        //  return ret.toLocaleString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // 并不需要json，直接用逗号分隔即可
        String[] list = data.split(",");
        TreeNode[] nodeList = new TreeNode[list.length];
        if (list.length == 0) {
            return null;
        }
        nodeList[0] = new TreeNode(Integer.parseInt(list[0]));
        for (int i = 1; i < list.length; i++) {
            if ("null".equals(list[i])) {
                continue;
            }
            int[] pos = getPos(i);
            boolean isLeft = ((i + 1) % 2 == 0);
            TreeNode node = new TreeNode(Integer.parseInt(list[i]));
            nodeList[i] = node;
            if (isLeft) {
                nodeList[pos[0]].left = node;
            } else {
                nodeList[pos[0]].right = node;
            }
        }
        return nodeList[0];
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));