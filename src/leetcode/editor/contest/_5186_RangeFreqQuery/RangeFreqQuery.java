package leetcode.editor.contest._5186_RangeFreqQuery;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

class TableNode {
    int index;
    int count;

    TableNode(int i, int c) {
        index = i;
        count = c;
    }
}

class RangeFreqQuery {
    // 使用树
    int[] totalCount;
    TableNode[] nodes;
    TreeSet<TableNode>[] table;
    int size;

    public RangeFreqQuery(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        size = arr.length;
        totalCount = new int[max + 1];
        nodes = new TableNode[arr.length];
        table = new TreeSet[max + 1];
        for (int i = 0; i < arr.length; i++) {
            // count从1开始
            totalCount[arr[i]]++;
            if (table[arr[i]] == null) {
                table[arr[i]] = new TreeSet<>(Comparator.comparingInt(o -> o.index));
            }
            TableNode tableNode = new TableNode(i, totalCount[arr[i]]);
            nodes[i] = tableNode;
            table[arr[i]].add(tableNode);
        }
    }

    public int query(int left, int right, int value) {
        if (value >= table.length || table[value] == null) {
            return 0;
        }
        TableNode leftNode, rightNode;
        leftNode = table[value].ceiling(nodes[left]);
        rightNode = table[value].floor(nodes[right]);
        if (rightNode == null || leftNode == null) {
            return 0;
        }
        return rightNode.count - leftNode.count + 1;
    }

    public static void main(String[] args) {
        RangeFreqQuery rangeFreqQuery = new RangeFreqQuery(new int[]{1, 1, 1, 2, 2});
        rangeFreqQuery.query(2, 2, 1);
    }
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery obj = new RangeFreqQuery(arr);
 * int param_1 = obj.query(left,right,value);
 */