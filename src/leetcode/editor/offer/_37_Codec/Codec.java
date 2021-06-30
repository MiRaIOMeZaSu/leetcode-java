package leetcode.editor.offer._37_Codec;


import java.util.*;


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
        arr.set(pos - 1, res);
        return res;
    }

    private void solve(TreeNode root, int pos_root, Map<Integer, Integer> map) {
        map.put(pos_root, root.val);
        int[] pos = getPos(pos_root);
        if (root.left != null) {
            solve(root.left, pos[1], map);
        }
        if (root.right != null) {
            solve(root.right, pos[2], map);
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // 使用深度优先,每个父节点为子节点设置序号
        Map<Integer, Integer> map = new HashMap<>();
        if (root != null) {
            solve(root, 0, map);
        }
        String string = "";
        for (Integer key : map.keySet()) {
            string += String.valueOf(key) + "|" + String.valueOf(map.get(key));
            string += ",";
        }
        return string;
        //  return ret.toLocaleString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // 并不需要json，直接用逗号分隔即可
        String[] list = data.split(",");
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < list.length; i++) {
            String[] entry = list[i].split("\\|");
            map.put(Integer.parseInt(entry[0]), Integer.parseInt(entry[1]));
        }
        HashMap<Integer, TreeNode> nodeMap = new HashMap<>();
        nodeMap.put(0, new TreeNode(map.get(0)));
        Deque<Integer> q = new LinkedList<>();
        q.add(0);
        while (!q.isEmpty()) {
            int pivot = q.poll();
            int[] pos = getPos(pivot);
            if (map.containsKey(pos[1])) {
                TreeNode temp = new TreeNode(map.get(pos[1]));
                nodeMap.get(pivot).left = temp;
                nodeMap.put(pos[1], temp);
                q.add(pos[1]);
            }
            if (map.containsKey(pos[2])) {
                TreeNode temp = new TreeNode(map.get(pos[2]));
                nodeMap.get(pivot).right = temp;
                nodeMap.put(pos[2], temp);
                q.add(pos[2]);
            }
        }
        return nodeMap.get(0);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));